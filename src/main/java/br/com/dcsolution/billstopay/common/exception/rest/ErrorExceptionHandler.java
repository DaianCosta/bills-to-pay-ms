package br.com.dcsolution.billstopay.common.exception.rest;

import br.com.dcsolution.billstopay.common.dto.ApiErrorDto;
import br.com.dcsolution.billstopay.common.dto.ApiErrorsDto;
import br.com.dcsolution.billstopay.common.exception.MessageBusiness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErrorExceptionHandler {

    Logger logger = LoggerFactory.getLogger(ErrorExceptionHandler.class);

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorsDto exceptionHandler(final Exception ex) {
        final String error = ex.getCause().toString();
        logger.info("Exception ms Bills to Pay");
        logger.error(error);

        List<ApiErrorDto> errors = new ArrayList<>();
        errors.add(new ApiErrorDto("error", MessageBusiness.ERROR_EXCEPTION));
        return new ApiErrorsDto(errors);
    }

}
