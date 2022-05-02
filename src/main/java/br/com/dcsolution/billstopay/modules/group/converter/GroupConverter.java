package br.com.dcsolution.billstopay.modules.group.converter;

import br.com.dcsolution.billstopay.common.converter.PageConverter;
import br.com.dcsolution.billstopay.modules.group.dto.GroupDto;
import br.com.dcsolution.billstopay.modules.group.entity.Group;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class GroupConverter extends PageConverter {

    private final ModelMapper modelMapper;

    @Autowired
    public GroupConverter(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Group dtoToEntity(final GroupDto dto) {
        return modelMapper.map(dto, Group.class);
    }

    public GroupDto entityToDto(final Group entity) {
        return modelMapper.map(entity, GroupDto.class);
    }

    public Page<GroupDto> pageEntityToPageDto(final Page<Group> groups) {
        return groups.map(this::entityToDto);
    }

}
