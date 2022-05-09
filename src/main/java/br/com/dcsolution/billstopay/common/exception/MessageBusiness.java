package br.com.dcsolution.billstopay.common.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MessageBusiness {
    public static final String EXISTING_RECORD = "Registro Existente";
    public static final String NO_RECORD = "Nenhum registro encontrado para este ID";
    public static final String ERROR_EXCEPTION = "Não foi possível processar essa requisição, tente mais tarde!";
}
