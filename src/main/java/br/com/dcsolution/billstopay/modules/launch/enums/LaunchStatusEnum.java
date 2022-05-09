package br.com.dcsolution.billstopay.modules.launch.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LaunchStatusEnum {
    WAITING("Aguardando"),
    PAID("Pago");

    private final String description;
}
