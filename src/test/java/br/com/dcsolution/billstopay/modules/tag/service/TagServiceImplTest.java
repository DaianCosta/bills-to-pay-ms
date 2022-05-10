package br.com.dcsolution.billstopay.modules.tag.service;

import br.com.dcsolution.billstopay.common.exception.BusinessException;
import br.com.dcsolution.billstopay.modules.tag.converter.TagConverter;
import br.com.dcsolution.billstopay.modules.tag.dto.TagDto;
import br.com.dcsolution.billstopay.modules.tag.entity.Tag;
import br.com.dcsolution.billstopay.modules.tag.repository.TagRepository;
import br.com.dcsolution.billstopay.modules.tag.stub.TagServiceStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class TagServiceImplTest {

    @Mock
    private TagRepository tagRepository;

    @Mock
    private TagConverter tagConverter;

    @InjectMocks
    private TagServiceImpl tagService;

    @Test
    void create() {
        final Tag tag = TagServiceStub.generateEntity();
        final TagDto tagDto = TagServiceStub.generateDto();

        Mockito.when(tagConverter.dtoToEntity(tagDto)).thenReturn(tag);
        Mockito.when(tagRepository.save(tag)).thenReturn(tag);
        tagService.create(tagDto);
        Mockito.verify(tagRepository, Mockito.times(1)).save(tag);
    }

    @Test
    void update() {
        final Tag tag = TagServiceStub.generateEntity();
        final TagDto tagDto = TagServiceStub.generateDto();

        Mockito.when(tagRepository.findById(1)).thenReturn(java.util.Optional.of(tag));
        Mockito.when(tagConverter.dtoToEntity(tagDto)).thenReturn(tag);
        Mockito.when(tagRepository.save(tag)).thenReturn(tag);
        tagService.update(tagDto);
        Mockito.verify(tagRepository, Mockito.times(1)).save(tag);
    }

    @Test
    void updateException() {
        final TagDto tagDto = TagServiceStub.generateDto();

        Mockito.when(tagRepository.findById(1)).thenReturn(Optional.empty());
        Assertions.assertThrows(BusinessException.class, () -> tagService.update(tagDto));
    }

    @Test
    void updateExceptionExistingRecord() {
        final Tag tag = TagServiceStub.generateEntity();
        final TagDto tagDto = TagServiceStub.generateDto();

        Mockito.when(tagRepository.findById(TagServiceStub.ID)).thenReturn(Optional.of(tag));
        Mockito.when(tagRepository.isExistName(TagServiceStub.NAME, TagServiceStub.ID)).thenReturn(Optional.of(tag));

        Assertions.assertThrows(BusinessException.class, () -> tagService.update(tagDto));
    }

    @Test
    void findAll() {

        final Page<Tag> pageGroup = TagServiceStub.generatePageEntity();

        Mockito.when(tagConverter.generatePageRequest(TagServiceStub.PAGE, TagServiceStub.SIZE))
                .thenReturn(TagServiceStub.generatePageRequest());
        Mockito.when(tagConverter.pageEntityToPageDto(pageGroup))
                .thenReturn(TagServiceStub.generatePageDto());
        Mockito.when(tagRepository.search(TagServiceStub.NAME, TagServiceStub.generatePageRequest()))
                .thenReturn(pageGroup);

        final Page<TagDto> result = tagService.findAll(TagServiceStub.PAGE,
                TagServiceStub.SIZE, TagServiceStub.NAME);
        Assertions.assertEquals(1, result.getTotalPages());
        Assertions.assertEquals(1, result.getTotalElements());
    }

    @Test
    void findByIdException() {
        Mockito.when(tagRepository.findById(1)).thenReturn(Optional.empty());
        Assertions.assertThrows(BusinessException.class, () -> tagService.findById(1));
    }

    @Test
    void findById() {
        final Tag tag = TagServiceStub.generateEntity();
        final TagDto tagDto = TagServiceStub.generateDto();

        Mockito.when(tagRepository.findById(1)).thenReturn(java.util.Optional.of(tag));
        Mockito.when(tagConverter.entityToDto(tag)).thenReturn(tagDto);

        final TagDto result = tagService.findById(1);

        Assertions.assertEquals(1, result.getId());
        Mockito.verify(tagRepository, Mockito.times(1)).findById(1);
    }

    @Test
    void deleteException() {
        Mockito.when(tagRepository.findById(1)).thenReturn(Optional.empty());
        Assertions.assertThrows(BusinessException.class, () -> tagService.delete(1));
    }

    @Test
    void delete() {
        final Tag tag = TagServiceStub.generateEntity();

        Mockito.when(tagRepository.findById(1)).thenReturn(java.util.Optional.of(tag));
        Mockito.doNothing().when(tagRepository).delete(tag);

        tagService.delete(1);

        Mockito.verify(tagRepository, Mockito.times(1)).delete(tag);
    }
}