package br.com.dcsolution.billstopay.modules.launch.service;

import br.com.dcsolution.billstopay.modules.launch.dto.LaunchDto;
import org.springframework.data.domain.Page;

public interface LaunchService {

    void create(final LaunchDto categoryDto);

    void update(final LaunchDto categoryDto);

    Page<LaunchDto> findAll(final Integer page,
                            final Integer size,
                            final String searchTerm);

    LaunchDto findById(final Integer id);

    void delete(final Integer id);
}
