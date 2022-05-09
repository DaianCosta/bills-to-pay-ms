package br.com.dcsolution.billstopay.modules.tag.service;

import br.com.dcsolution.billstopay.modules.tag.dto.TagDto;
import org.springframework.data.domain.Page;

public interface TagService {

    void create(final TagDto groupDto);

    void update(final TagDto groupDto);

    Page<TagDto> findAll(final Integer page,
                         final Integer size,
                         final String searchTerm);

    TagDto findById(final Integer id);

    void delete(final Integer id);
}
