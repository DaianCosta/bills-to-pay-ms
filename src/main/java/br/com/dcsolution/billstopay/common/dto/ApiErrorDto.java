package br.com.dcsolution.billstopay.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiErrorDto {
    private final String key;
    private final String value;
}