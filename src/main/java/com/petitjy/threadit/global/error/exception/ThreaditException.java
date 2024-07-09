package com.petitjy.threadit.global.error.exception;

import com.petitjy.threadit.global.error.code.ThreaditErrorCode;
import lombok.Getter;

@Getter
public class ThreaditException extends RuntimeException {
    private final ThreaditErrorCode errorCode;

    protected ThreaditException(ThreaditErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    protected static ThreaditException of(ThreaditErrorCode errorCode) {
        return new ThreaditException(errorCode);
    }
}
