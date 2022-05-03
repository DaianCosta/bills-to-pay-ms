package br.com.dcsolution.billstopay.modules.category.converter;

import br.com.dcsolution.billstopay.common.converter.PageConverter;
import br.com.dcsolution.billstopay.modules.category.dto.CategoryDto;
import br.com.dcsolution.billstopay.modules.category.entity.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter extends PageConverter {

    private final ModelMapper modelMapper;

    @Autowired
    public CategoryConverter(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Category dtoToEntity(final CategoryDto dto) {
        return modelMapper.map(dto, Category.class);
    }

    public CategoryDto entityToDto(final Category entity) {
        return modelMapper.map(entity, CategoryDto.class);
    }

    public Page<CategoryDto> pageEntityToPageDto(final Page<Category> groups) {
        return groups.map(this::entityToDto);
    }

}
