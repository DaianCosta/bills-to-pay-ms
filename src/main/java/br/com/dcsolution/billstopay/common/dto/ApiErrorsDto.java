package br.com.dcsolution.billstopay.common.dto;

import lombok.Getter;

import java.util.List;

public class ApiErrorsDto {

    @Getter
    private final List<ApiErrorDto> errors;

    public ApiErrorsDto(List<ApiErrorDto> errors) {
        this.errors = errors;
    }


}