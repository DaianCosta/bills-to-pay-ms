package br.com.dcsolution.billstopay.modules.launch.stub;

import br.com.dcsolution.billstopay.common.dto.PaginationDto;
import br.com.dcsolution.billstopay.modules.category.entity.Category;
import br.com.dcsolution.billstopay.modules.launch.dto.LaunchDetailDto;
import br.com.dcsolution.billstopay.modules.launch.dto.LaunchDto;
import br.com.dcsolution.billstopay.modules.launch.entity.Launch;
import br.com.dcsolution.billstopay.modules.launch.enums.LaunchStatusEnum;
import br.com.dcsolution.billstopay.modules.launch.enums.LaunchTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class LaunchServiceStub {
    public static final String DESCRIPTION = "comida";
    public static final Integer ID = 1;
    public static final LocalDate DATE = LocalDate.now();
    public static final String DATE_FORMAT = "22/02/2022";
    public static final Integer CATEGORY = 1;
    public static final String NAME_CATEGORY = "refeicao";
    public static final Integer START = 1;
    public static final Integer END = 1;
    public static final LaunchStatusEnum STATUS = LaunchStatusEnum.WAITING;
    public static final String STATUS_DESCRIPTION = LaunchStatusEnum.WAITING.getDescription();
    public static final LaunchTypeEnum TYPE = LaunchTypeEnum.IN;
    public static final String TYPE_DESCRIPTION = LaunchTypeEnum.IN.getDescription();
    public static final BigDecimal VALUE = BigDecimal.ONE;
    public static final String VALUE_FORMAT = "R$ 1.00";

    public static final Integer PAGE = 1;
    public static final Integer SIZE = 10;

    public static LaunchDto generateDto(final Integer id, final String description) {
        final LaunchDto launchDto = new LaunchDto();
        launchDto.setId(id);
        launchDto.setDescription(description);
        launchDto.setCategoryId(CATEGORY);
        launchDto.setPaymentDate(DATE);
        launchDto.setStatus(STATUS);
        launchDto.setStartPosition(START);
        launchDto.setEndPosition(END);
        launchDto.setType(TYPE);
        launchDto.setPaymentValue(VALUE);

        final List<Integer> tags = new ArrayList<>();
        tags.add(1);

        launchDto.setTags(tags);
        return launchDto;
    }

    public static LaunchDetailDto generateDtoDetail() {
        final LaunchDetailDto launchDto = new LaunchDetailDto();
        launchDto.setId(ID);
        launchDto.setDescription(DESCRIPTION);
        launchDto.setNameCategory(NAME_CATEGORY);
        launchDto.setPaymentDateFormat(DATE_FORMAT);
        launchDto.setStatus(STATUS_DESCRIPTION);
        launchDto.setStartPosition(START);
        launchDto.setEndPosition(END);
        launchDto.setType(TYPE_DESCRIPTION);
        launchDto.setPaymentValueFormat(VALUE_FORMAT);
        return launchDto;
    }

    public static Launch generateEntity() {
        final Launch launch = new Launch();
        launch.setId(ID);
        launch.setDescription(DESCRIPTION);
        launch.setCategory(new Category(CATEGORY, NAME_CATEGORY));
        launch.setPaymentDate(DATE);
        launch.setStatus(STATUS);
        launch.setStartPosition(START);
        launch.setEndPosition(END);
        launch.setType(TYPE);
        launch.setPaymentValue(VALUE);
        return launch;
    }

    public static Page<Launch> generatePageEntity() {
        final List<Launch> lances = new ArrayList<>();
        lances.add(generateEntity());
        return new PageImpl<>(lances);
    }

    public static PaginationDto<LaunchDetailDto> generatePageDto() {
        final List<LaunchDetailDto> launches = new ArrayList<>();
        launches.add(generateDtoDetail());

        return new PaginationDto<>(1L, 1, 1, 1, launches);
    }

    public static PageRequest generatePageRequest() {
        return PageRequest.of(0, 10);
    }


}
