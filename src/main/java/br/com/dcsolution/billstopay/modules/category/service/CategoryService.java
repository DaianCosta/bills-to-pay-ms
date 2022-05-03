package br.com.dcsolution.billstopay.modules.category.service;

import br.com.dcsolution.billstopay.modules.category.dto.CategoryDto;
import org.springframework.data.domain.Page;

public interface CategoryService {

    void create(final CategoryDto categoryDto);

    void update(final CategoryDto categoryDto);

    Page<CategoryDto> findAll(final Integer page,
                              final Integer size,
                              final String searchTerm);

    CategoryDto findById(final Integer id);

    void delete(final Integer id);
}
