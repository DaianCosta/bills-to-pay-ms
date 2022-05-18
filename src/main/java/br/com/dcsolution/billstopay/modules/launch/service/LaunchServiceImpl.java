package br.com.dcsolution.billstopay.modules.launch.service;

import br.com.dcsolution.billstopay.common.dto.PaginationDto;
import br.com.dcsolution.billstopay.common.exception.BusinessException;
import br.com.dcsolution.billstopay.common.exception.MessageBusiness;
import br.com.dcsolution.billstopay.modules.category.entity.Category;
import br.com.dcsolution.billstopay.modules.category.repository.CategoryRepository;
import br.com.dcsolution.billstopay.modules.launch.converter.LaunchConverter;
import br.com.dcsolution.billstopay.modules.launch.dto.LaunchDetailDto;
import br.com.dcsolution.billstopay.modules.launch.dto.LaunchDto;
import br.com.dcsolution.billstopay.modules.launch.entity.Launch;
import br.com.dcsolution.billstopay.modules.launch.entity.TagLaunch;
import br.com.dcsolution.billstopay.modules.launch.repository.LaunchRepository;
import br.com.dcsolution.billstopay.modules.tag.entity.Tag;
import br.com.dcsolution.billstopay.modules.tag.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class LaunchServiceImpl implements LaunchService {

    private final LaunchRepository launchRepository;
    private final LaunchConverter launchConverter;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;

    @Autowired
    LaunchServiceImpl(final LaunchRepository launchRepository,
                      final LaunchConverter launchConverter,
                      final CategoryRepository categoryRepository,
                      final TagRepository tagRepository) {
        this.launchRepository = launchRepository;
        this.launchConverter = launchConverter;
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    @Transactional
    public void create(final LaunchDto launchDto) {
        validateExistingDescription(launchDto);
        launchRepository.save(dtoToEntity(launchDto));
    }

    @Override
    public void update(final LaunchDto launchDto) {
        validateNoRecord(launchDto);
        validateExistingDescription(launchDto);
        launchRepository.save(dtoToEntity(launchDto));
    }

    @Override
    public PaginationDto<LaunchDetailDto> findAll(final Integer page,
                                                  final Integer size,
                                                  final String searchTerm) {
        final Page<Launch> launches = launchRepository.search(searchTerm,
                launchConverter.generatePageRequest(page, size));
        return launchConverter.pageEntityToPageDto(launches);
    }

    @Override
    public LaunchDetailDto findById(final Integer id) {
        final Launch category = launchRepository.findById(id)
                .orElseThrow(() -> new BusinessException(MessageBusiness.NO_RECORD));

        return launchConverter.entityToDetailDto(category);
    }

    @Override
    public void delete(final Integer id) {
        final Launch category = launchRepository.findById(id)
                .orElseThrow(() -> new BusinessException(MessageBusiness.NO_RECORD));

        launchRepository.delete(category);
    }

    private void validateNoRecord(final LaunchDto launchDto) {
        final Optional<Launch> launch = launchRepository.findById(launchDto.getId());

        if (launch.isEmpty()) {
            throw new BusinessException(MessageBusiness.NO_RECORD);
        }
    }

    private void validateExistingDescription(final LaunchDto launchDto) {
        final Integer id = launchDto.getId() == null ? 0 : launchDto.getId();
        final Optional<Launch> launch = launchRepository
                .isExistDescription(launchDto.getDescription(), id, launchDto.getPaymentDate());

        if (launch.isPresent()) {
            throw new BusinessException(MessageBusiness.EXISTING_RECORD_LAUNCH);
        }
    }

    private Launch dtoToEntity(final LaunchDto dto) {
        final Launch launch = launchConverter.dtoToEntity(dto);

        final Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new BusinessException(MessageBusiness.NO_RECORD_CATEGORY));

        launch.setCategory(category);

        if (!dto.getTags().isEmpty()) {
            launch.setTags(converterTagsToEntity(dto.getTags()));
        }

        return launch;
    }

    private List<TagLaunch> converterTagsToEntity(final List<Integer> tags) {
        return tags
                .stream()
                .map(tagId -> {
                    final Tag tag = tagRepository.findById(tagId)
                            .orElseThrow(() -> new BusinessException(MessageBusiness.NO_RECORD_TAG));

                    final TagLaunch tagLaunch = new TagLaunch();
                    tagLaunch.setTag(tag);

                    return tagLaunch;
                }).collect(Collectors.toList());
    }
}
