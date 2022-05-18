package br.com.dcsolution.billstopay.modules.tag.service;

import br.com.dcsolution.billstopay.common.dto.PaginationDto;
import br.com.dcsolution.billstopay.modules.tag.dto.TagDto;

public interface TagService {

    void create(final TagDto groupDto);

    void update(final TagDto groupDto);

    PaginationDto<TagDto> findAll(final Integer page,
                                  final Integer size,
                                  final String searchTerm);

    TagDto findById(final Integer id);

    void delete(final Integer id);
}
