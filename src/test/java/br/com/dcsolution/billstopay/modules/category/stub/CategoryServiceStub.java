package br.com.dcsolution.billstopay.modules.category.stub;

import br.com.dcsolution.billstopay.common.dto.PaginationDto;
import br.com.dcsolution.billstopay.modules.category.dto.CategoryDto;
import br.com.dcsolution.billstopay.modules.category.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

public final class CategoryServiceStub {
    public static final String NAME = "comida";
    public static final Integer ID = 1;
    public static final Integer PAGE = 1;
    public static final Integer SIZE = 10;

    public static CategoryDto generateDto() {
        final CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(ID);
        categoryDto.setName(NAME);
        return categoryDto;
    }

    public static CategoryDto generateDtoParameter(final Integer id, final String name) {
        final CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(id);
        categoryDto.setName(name);
        return categoryDto;
    }

    public static Category generateEntity() {
        final Category category = new Category();
        category.setId(ID);
        category.setName(NAME);
        return category;
    }

    public static Page<Category> generatePageEntity() {
        final List<Category> categories = new ArrayList<>();
        categories.add(new Category(ID, NAME));

        return new PageImpl<>(categories);
    }

    public static PaginationDto<CategoryDto> generatePageDto() {
        final List<CategoryDto> categories = new ArrayList<>();
        categories.add(new CategoryDto(ID, NAME));

        return new PaginationDto<>(1L, 1, 1, 1, categories);
    }

    public static PageRequest generatePageRequest() {
        return PageRequest.of(0, 10);
    }

}
