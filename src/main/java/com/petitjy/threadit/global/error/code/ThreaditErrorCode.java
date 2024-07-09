package com.petitjy.threadit.global.error.code;

import org.springframework.http.HttpStatus;

public interface ThreaditErrorCode {

    HttpStatus getStatus();
    String getCode();
    String getMessage();
}
