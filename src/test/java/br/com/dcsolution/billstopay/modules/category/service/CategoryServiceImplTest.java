package br.com.dcsolution.billstopay.modules.category.service;

import br.com.dcsolution.billstopay.common.exception.BusinessException;
import br.com.dcsolution.billstopay.modules.category.converter.CategoryConverter;
import br.com.dcsolution.billstopay.modules.category.dto.CategoryDto;
import br.com.dcsolution.billstopay.modules.category.entity.Category;
import br.com.dcsolution.billstopay.modules.category.repository.CategoryRepository;
import br.com.dcsolution.billstopay.modules.category.stub.CategoryServiceStub;
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
class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryConverter categoryConverter;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    void create() {
        final Category category = CategoryServiceStub.generateEntity();
        final CategoryDto categoryDto = CategoryServiceStub.generateDto();

        Mockito.when(categoryConverter.dtoToEntity(categoryDto)).thenReturn(category);
        Mockito.when(categoryRepository.save(category)).thenReturn(category);
        categoryService.create(categoryDto);
        Mockito.verify(categoryRepository, Mockito.times(1)).save(category);
    }

    @Test
    void update() {
        final Category category = CategoryServiceStub.generateEntity();
        final CategoryDto categoryDto = CategoryServiceStub.generateDto();

        Mockito.when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
        Mockito.when(categoryConverter.dtoToEntity(categoryDto)).thenReturn(category);
        Mockito.when(categoryRepository.save(category)).thenReturn(category);
        categoryService.update(categoryDto);
        Mockito.verify(categoryRepository, Mockito.times(1)).save(category);
    }

    @Test
    void updateException() {
        final CategoryDto categoryDto = CategoryServiceStub.generateDto();

        Mockito.when(categoryRepository.findById(1)).thenReturn(Optional.empty());
        Assertions.assertThrows(BusinessException.class, () -> categoryService.update(categoryDto));
    }

    @Test
    void updateExceptionExistingRecord() {
        final Category category = CategoryServiceStub.generateEntity();
        final CategoryDto categoryDto = CategoryServiceStub.generateDto();

        Mockito.when(categoryRepository.findById(CategoryServiceStub.ID)).thenReturn(Optional.of(category));
        Mockito.when(categoryRepository
                .isExistName(CategoryServiceStub.NAME, CategoryServiceStub.ID)).thenReturn(Optional.of(category));

        Assertions.assertThrows(BusinessException.class, () -> categoryService.update(categoryDto));
    }

    @Test
    void findAll() {

        final Page<Category> pageCategory = CategoryServiceStub.generatePageEntity();

        Mockito.when(categoryConverter.generatePageRequest(CategoryServiceStub.PAGE, CategoryServiceStub.SIZE))
                .thenReturn(CategoryServiceStub.generatePageRequest());
        Mockito.when(categoryConverter.pageEntityToPageDto(pageCategory))
                .thenReturn(CategoryServiceStub.generatePageDto());
        Mockito.when(categoryRepository.search(CategoryServiceStub.NAME, CategoryServiceStub.generatePageRequest()))
                .thenReturn(pageCategory);

        final Page<CategoryDto> result = categoryService.findAll(CategoryServiceStub.PAGE,
                CategoryServiceStub.SIZE, CategoryServiceStub.NAME);
        Assertions.assertEquals(1, result.getTotalPages());
        Assertions.assertEquals(1, result.getTotalElements());
    }

    @Test
    void findByIdException() {
        Mockito.when(categoryRepository.findById(1)).thenReturn(Optional.empty());
        Assertions.assertThrows(BusinessException.class, () -> categoryService.findById(1));
    }

    @Test
    void findById() {
        final Category category = CategoryServiceStub.generateEntity();
        final CategoryDto categoryDto = CategoryServiceStub.generateDto();

        Mockito.when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
        Mockito.when(categoryConverter.entityToDto(category)).thenReturn(categoryDto);

        final CategoryDto result = categoryService.findById(1);

        Assertions.assertEquals(1, result.getId());
        Mockito.verify(categoryRepository, Mockito.times(1)).findById(1);
    }

    @Test
    void deleteException() {
        Mockito.when(categoryRepository.findById(1)).thenReturn(Optional.empty());
        Assertions.assertThrows(BusinessException.class, () -> categoryService.delete(1));
    }

    @Test
    void delete() {
        final Category category = CategoryServiceStub.generateEntity();

        Mockito.when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
        Mockito.doNothing().when(categoryRepository).delete(category);

        categoryService.delete(1);

        Mockito.verify(categoryRepository, Mockito.times(1)).delete(category);
    }
}