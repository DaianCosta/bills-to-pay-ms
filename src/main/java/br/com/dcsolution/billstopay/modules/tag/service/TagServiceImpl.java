package br.com.dcsolution.billstopay.modules.tag.service;

import br.com.dcsolution.billstopay.common.exception.BusinessException;
import br.com.dcsolution.billstopay.common.exception.MessageBusiness;
import br.com.dcsolution.billstopay.modules.tag.converter.TagConverter;
import br.com.dcsolution.billstopay.modules.tag.dto.TagDto;
import br.com.dcsolution.billstopay.modules.tag.entity.Tag;
import br.com.dcsolution.billstopay.modules.tag.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final TagConverter tagConverter;

    @Autowired
    TagServiceImpl(final TagRepository tagRepository,
                   final TagConverter tagConverter) {
        this.tagRepository = tagRepository;
        this.tagConverter = tagConverter;
    }

    @Override
    public void create(final TagDto tagDto) {
        validateExistingName(tagDto);
        tagRepository.save(tagConverter.dtoToEntity(tagDto));
    }

    @Override
    public void update(final TagDto tagDto) {

        validateNoRecord(tagDto);
        validateExistingName(tagDto);
        tagRepository.save(tagConverter.dtoToEntity(tagDto));
    }

    @Override
    public Page<TagDto> findAll(final Integer page,
                                final Integer size,
                                final String searchTerm) {
        final Page<Tag> tags = tagRepository.search(searchTerm, tagConverter.generatePageRequest(page, size));
        return tagConverter.pageEntityToPageDto(tags);
    }

    @Override
    public TagDto findById(final Integer id) {
        final Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new BusinessException(MessageBusiness.NO_RECORD));

        return tagConverter.entityToDto(tag);
    }

    @Override
    public void delete(final Integer id) {
        final Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new BusinessException(MessageBusiness.NO_RECORD));

        tagRepository.delete(tag);
    }

    void validateNoRecord(final TagDto tagDto) {
        final Optional<Tag> tag = tagRepository.findById(tagDto.getId());

        if (tag.isEmpty()) {
            throw new BusinessException(MessageBusiness.NO_RECORD);
        }
    }

    void validateExistingName(final TagDto tagDto) {
        final Optional<Tag> tag = tagRepository.isExistName(tagDto.getName(), tagDto.getId());

        if (tag.isPresent()) {
            throw new BusinessException(MessageBusiness.EXISTING_RECORD);
        }
    }
}
