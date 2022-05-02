package br.com.dcsolution.billstopay.modules.group.service;

import br.com.dcsolution.billstopay.modules.group.dto.GroupDto;
import org.springframework.data.domain.Page;

public interface GroupService {

    void create(final GroupDto groupDto);

    void update(final GroupDto groupDto);

    Page<GroupDto> findAll(final Integer page,
                           final Integer size,
                           final String searchTerm);

    GroupDto findById(final Integer id);

    void delete(final Integer id);
}
