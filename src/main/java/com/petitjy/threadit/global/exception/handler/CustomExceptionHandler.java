package com.petitjy.threadit.global.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.petitjy.threadit.global.dto.ApiResponse;
import com.petitjy.threadit.global.exception.EntityNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

	// TODO: 도메인 별 분리 가능성 있음

	@ExceptionHandler(EntityNotFoundException.class)
	public <T> ApiResponse<T> handleEntityException(EntityNotFoundException exception) {
		log.info("CustomExceptionHandler.handleEntityException() is called.");
		return ApiResponse.<T>builder()
			.status(HttpStatus.OK)
			.code(exception.getErrorCode().getErrorCode())
			.msg(exception.getErrorCode().getMsg())
			.build();
	}
}
