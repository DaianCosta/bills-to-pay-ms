package br.com.dcsolution.billstopay.modules.launch.converter;

import br.com.dcsolution.billstopay.common.converter.PageConverter;
import br.com.dcsolution.billstopay.common.dto.PaginationDto;
import br.com.dcsolution.billstopay.modules.launch.dto.LaunchDetailDto;
import br.com.dcsolution.billstopay.modules.launch.dto.LaunchDto;
import br.com.dcsolution.billstopay.modules.launch.entity.Launch;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

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

    public LaunchDetailDto entityToDetailDto(final Launch entity) {
        final LaunchDetailDto launchDetailDto = modelMapper.map(entity, LaunchDetailDto.class);

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        final DecimalFormat decimalFormat = new DecimalFormat("R$ #,###,##0.00");

        launchDetailDto.setPaymentDateFormat(entity.getPaymentDate().format(formatter));
        launchDetailDto.setStatus(entity.getStatus().getDescription());
        launchDetailDto.setType(entity.getType().getDescription());
        launchDetailDto.setNameCategory(entity.getCategory().getName());
        launchDetailDto.setPaymentValueFormat(decimalFormat.format(entity.getPaymentValue()));
        if (entity.getTags() != null && !entity.getTags().isEmpty()) {
            final List<String> tags = entity.getTags().stream().map(tag -> tag.getTag()
                    .getName()).collect(Collectors.toList());
            launchDetailDto.setTags(tags);
        }

        return launchDetailDto;
    }

    public PaginationDto<LaunchDetailDto> pageEntityToPageDto(final Page<Launch> items) {
        return new PaginationDto<>(items.getTotalElements(),
                items.getTotalPages(),
                items.getNumber(),
                items.getSize(),
                items.getContent().stream().map(this::entityToDetailDto).collect(Collectors.toList())
        );
    }

}
