package br.com.dcsolution.billstopay.modules.launch.stub;

import br.com.dcsolution.billstopay.modules.launch.dto.LaunchDto;
import br.com.dcsolution.billstopay.modules.launch.entity.Launch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

public final class LaunchServiceStub {
    public static final String DESCRIPTION = "comida";
    public static final Integer ID = 1;
    public static final Integer PAGE = 1;
    public static final Integer SIZE = 10;

    /*

    public static LaunchDto generateDto() {
        final LaunchDto launchDto = new LaunchDto();
        launchDto.setId(ID);
        launchDto.setDescription(DESCRIPTION);
        return launchDto;
    }

    public static LaunchDto generateDtoParameter(final Integer id, final String name) {
        final LaunchDto launchDto = new LaunchDto();
        launchDto.setId(id);
        launchDto.setDescription(name);
        return launchDto;
    }

    public static Launch generateEntity() {
        final Launch launch = new Launch();
        launch.setId(ID);
        launch.setDescription(DESCRIPTION);
        return launch;
    }

    public static Page<Launch> generatePageEntity() {
        final List<Launch> lances = new ArrayList<>();
        lances.add(new Launch(ID, DESCRIPTION));

        return new PageImpl<>(lances);
    }

    public static Page<LaunchDto> generatePageDto() {
        final List<LaunchDto> categories = new ArrayList<>();
        categories.add(new LaunchDto(ID, DESCRIPTION));

        return new PageImpl<>(categories);
    }

    public static PageRequest generatePageRequest() {
        return PageRequest.of(0, 10);
    }

     */

}
