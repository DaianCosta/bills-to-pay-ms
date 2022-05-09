package br.com.dcsolution.billstopay.modules.category.service;

import br.com.dcsolution.billstopay.common.exception.BusinessException;
import br.com.dcsolution.billstopay.common.exception.MessageBusiness;
import br.com.dcsolution.billstopay.modules.category.converter.CategoryConverter;
import br.com.dcsolution.billstopay.modules.category.dto.CategoryDto;
import br.com.dcsolution.billstopay.modules.category.entity.Category;
import br.com.dcsolution.billstopay.modules.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;

    @Autowired
    CategoryServiceImpl(final CategoryRepository categoryRepository,
                        final CategoryConverter categoryConverter) {
        this.categoryRepository = categoryRepository;
        this.categoryConverter = categoryConverter;
    }

    @Override
    public void create(final CategoryDto categoryDto) {
        validateExistingName(categoryDto);
        categoryRepository.save(categoryConverter.dtoToEntity(categoryDto));
    }

    @Override
    public void update(final CategoryDto categoryDto) {

        validateNoRecord(categoryDto);
        validateExistingName(categoryDto);
        categoryRepository.save(categoryConverter.dtoToEntity(categoryDto));
    }

    @Override
    public Page<CategoryDto> findAll(final Integer page,
                                     final Integer size,
                                     final String searchTerm) {
        final Page<Category> categories = categoryRepository.search(searchTerm,
                categoryConverter.generatePageRequest(page, size));
        return categoryConverter.pageEntityToPageDto(categories);
    }

    @Override
    public CategoryDto findById(final Integer id) {
        final Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new BusinessException(MessageBusiness.NO_RECORD));

        return categoryConverter.entityToDto(category);
    }

    @Override
    public void delete(final Integer id) {
        final Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new BusinessException(MessageBusiness.NO_RECORD));

        categoryRepository.delete(category);
    }

    void validateNoRecord(final CategoryDto categoryDto) {
        final Optional<Category> category = categoryRepository.findById(categoryDto.getId());

        if (category.isEmpty()) {
            throw new BusinessException(MessageBusiness.NO_RECORD);
        }
    }

    void validateExistingName(final CategoryDto categoryDto) {
        final Optional<Category> category = categoryRepository.isExistName(categoryDto.getName(), categoryDto.getId());

        if (category.isPresent()) {
            throw new BusinessException(MessageBusiness.EXISTING_RECORD);
        }
    }
}
