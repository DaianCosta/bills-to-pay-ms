package br.com.dcsolution.billstopay.common.converter;

import org.springframework.data.domain.PageRequest;

import java.util.Objects;

public abstract class PageConverter {

    private static final Integer SIZE_DEFAULT = 10;

    public PageRequest generatePageRequest(final Integer currentPage, final Integer size) {
        return PageRequest.of(Objects.requireNonNullElse(currentPage, 0),
                Objects.requireNonNullElse(size, SIZE_DEFAULT));
    }
}
