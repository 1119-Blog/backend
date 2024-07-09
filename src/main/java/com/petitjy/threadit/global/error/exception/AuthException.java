package com.petitjy.threadit.global.error.exception;

import com.petitjy.threadit.global.error.code.ThreaditErrorCode;
import lombok.Getter;

@Getter
public class AuthException extends ThreaditException{
    private AuthException(ThreaditErrorCode errorCode) {
        super(errorCode);
    }

    public static AuthException of(ThreaditErrorCode errorCode) {
        return new AuthException(errorCode);
    }
}
