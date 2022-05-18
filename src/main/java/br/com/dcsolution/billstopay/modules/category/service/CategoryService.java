package br.com.dcsolution.billstopay.modules.category.service;

import br.com.dcsolution.billstopay.common.dto.PaginationDto;
import br.com.dcsolution.billstopay.modules.category.dto.CategoryDto;

public interface CategoryService {

    void create(final CategoryDto categoryDto);

    void update(final CategoryDto categoryDto);

    PaginationDto<CategoryDto> findAll(final Integer page,
                                       final Integer size,
                                       final String searchTerm);

    CategoryDto findById(final Integer id);

    void delete(final Integer id);
}
