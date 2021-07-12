package org.musinsa.config.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    NOT_FOUND(404,"COMMON-ERR-404","PAGE NOT FOUND"),
    INTER_SERVER_ERROR(500,"COMMON-ERR-500","INTER SERVER ERROR"),
    INVALID_REQUEST(400,"ERR-400","INVALID REQUEST"),
    DUPLICATE_REQUEST(409,"ERR-409","DUPLICATE REQUEST");

    private int status;
    private String errorCode;
    private String msg;
}

