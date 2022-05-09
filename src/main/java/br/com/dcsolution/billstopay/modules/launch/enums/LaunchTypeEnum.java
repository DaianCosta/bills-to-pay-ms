package br.com.dcsolution.billstopay.modules.launch.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LaunchTypeEnum {
    IN("Entrada"),
    OUT("Saída");

    private final String description;
}
