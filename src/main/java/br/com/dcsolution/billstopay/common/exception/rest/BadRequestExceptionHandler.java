package br.com.dcsolution.billstopay.common.exception.rest;

import br.com.dcsolution.billstopay.common.dto.ApiErrorDto;
import br.com.dcsolution.billstopay.common.dto.ApiErrorsDto;
import br.com.dcsolution.billstopay.common.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class BadRequestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorsDto handleMethodNotValidException(final MethodArgumentNotValidException ex) {

        List<ApiErrorDto> errors = new ArrayList<>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(new ApiErrorDto(error.getField(), error.getDefaultMessage()));
        }

        return new ApiErrorsDto(errors);
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorsDto handleBusinessException(final BusinessException ex) {

        List<ApiErrorDto> errors = new ArrayList<>();
        errors.add(new ApiErrorDto("business", ex.getMessage()));
        return new ApiErrorsDto(errors);
    }

}