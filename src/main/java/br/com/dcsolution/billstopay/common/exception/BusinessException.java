package br.com.dcsolution.billstopay.common.exception;

public class BusinessException extends RuntimeException{
    public BusinessException(final String message){
        super(message);
    }
}
