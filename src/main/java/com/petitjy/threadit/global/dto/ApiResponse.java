package com.petitjy.threadit.global.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiResponse<T> {

    private HttpStatus status;
    private String msg;
    private T data;

    private ApiResponse(HttpStatus status, String msg, T data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ApiResponse<T> onSuccess(HttpStatus status, String msg) {
        return new ApiResponse<>(status, msg, null);
    }

    public static <T> ApiResponse<T> onSuccess(HttpStatus status, String msg, T data) {
        return new ApiResponse<>(status, msg, data);
    }
}