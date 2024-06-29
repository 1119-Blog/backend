package com.petitjy.threadit.global.dto;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ApiResponse<T> {
	private HttpStatus status;
	private String msg;
	private String code;
	private T data;

	public static <T> ApiResponse<T> onSuccess(HttpStatus status, String msg) {
		return ApiResponse.<T>builder()
			.status(status)
			.msg(msg)
			.build();
	}

	public static <T> ApiResponse<T> onSuccess(HttpStatus status, String msg, T data) {
		return ApiResponse.<T>builder()
			.status(status)
			.msg(msg)
			.data(data)
			.build();
	}

	public static <T> ApiResponse<T> onFailure(HttpStatus status, String msg, String code) {
		return ApiResponse.<T>builder()
			.status(status)
			.msg(msg)
			.code(code)
			.build();
	}
}
