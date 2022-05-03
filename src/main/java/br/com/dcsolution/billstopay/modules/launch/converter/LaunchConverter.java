package br.com.dcsolution.billstopay.modules.launch.converter;

import br.com.dcsolution.billstopay.common.converter.PageConverter;
import br.com.dcsolution.billstopay.modules.launch.dto.LaunchDto;
import br.com.dcsolution.billstopay.modules.launch.entity.Launch;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class LaunchConverter extends PageConverter {

    private final ModelMapper modelMapper;

    @Autowired
    public LaunchConverter(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Launch dtoToEntity(final LaunchDto dto) {
        return modelMapper.map(dto, Launch.class);
    }

    public LaunchDto entityToDto(final Launch entity) {
        return modelMapper.map(entity, LaunchDto.class);
    }

    public Page<LaunchDto> pageEntityToPageDto(final Page<Launch> groups) {
        return groups.map(this::entityToDto);
    }

}
