package org.musinsa.config.exception;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private int status;
    private String message;
    private String code;

    public ErrorResponse(ErrorCode errorCode){
        this.status = errorCode.getStatus();
        this.message = errorCode.getMsg();
        this.code = errorCode.getErrorCode();
    }
}
