package br.com.dcsolution.billstopay.modules.launch.service;

import br.com.dcsolution.billstopay.common.dto.PaginationDto;
import br.com.dcsolution.billstopay.modules.launch.dto.LaunchDetailDto;
import br.com.dcsolution.billstopay.modules.launch.dto.LaunchDto;

public interface LaunchService {

    void create(final LaunchDto categoryDto);

    void update(final LaunchDto categoryDto);

    PaginationDto<LaunchDetailDto> findAll(final Integer page,
                                           final Integer size,
                                           final String searchTerm);

    LaunchDetailDto findById(final Integer id);

    void delete(final Integer id);
}
