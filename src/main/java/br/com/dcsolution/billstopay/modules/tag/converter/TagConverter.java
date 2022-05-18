package br.com.dcsolution.billstopay.modules.tag.converter;

import br.com.dcsolution.billstopay.common.converter.PageConverter;
import br.com.dcsolution.billstopay.common.dto.PaginationDto;
import br.com.dcsolution.billstopay.modules.category.dto.CategoryDto;
import br.com.dcsolution.billstopay.modules.category.entity.Category;
import br.com.dcsolution.billstopay.modules.tag.dto.TagDto;
import br.com.dcsolution.billstopay.modules.tag.entity.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TagConverter extends PageConverter {

    private final ModelMapper modelMapper;

    @Autowired
    public TagConverter(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Tag dtoToEntity(final TagDto dto) {
        return modelMapper.map(dto, Tag.class);
    }

    public TagDto entityToDto(final Tag entity) {
        return modelMapper.map(entity, TagDto.class);
    }

    public PaginationDto<TagDto> pageEntityToPageDto(final Page<Tag> items) {
        return new PaginationDto<>(items.getTotalElements(),
                items.getTotalPages(),
                items.getNumber(),
                items.getSize(),
                items.getContent().stream().map(this::entityToDto).collect(Collectors.toList()));
    }

}
