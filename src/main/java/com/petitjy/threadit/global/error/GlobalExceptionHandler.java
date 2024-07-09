package com.petitjy.threadit.global.error;

import com.petitjy.threadit.global.error.code.ThreaditErrorCode;
import com.petitjy.threadit.global.error.dto.ErrorResponse;
import com.petitjy.threadit.global.error.exception.ThreaditException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleThreaditException(ThreaditException e) {
        log.error("Error: ", e);
        return createErrorResponse(e.getErrorCode());
    }


    private ResponseEntity<ErrorResponse> createErrorResponse(ThreaditErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getStatus()).body(ErrorResponse.of(errorCode));
    }
}
