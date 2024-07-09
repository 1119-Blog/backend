package com.petitjy.threadit.global.error.dto;

import com.petitjy.threadit.global.error.code.ThreaditErrorCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {
    private final String code;
    private final String message;

    /**
     * Creates an ErrorResponse object from a given ThreaditErrorCode and returns it wrapped in a ResponseEntity.
     * The ResponseEntity's status will be set to the status field of the provided ThreaditErrorCode.
     *
     * @param errorCode The ThreaditErrorCode containing the error information.
     * @return A ResponseEntity containing the ErrorResponse with the appropriate status.
     */
    public static ErrorResponse of(ThreaditErrorCode errorCode) {
        return new ErrorResponse(errorCode.getCode(), errorCode.getMessage());
    }
}
