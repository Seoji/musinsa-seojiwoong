package org.musinsa.config.exception;


import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException {

    private ErrorCode errorCode;

    public GeneralException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}