package com.petitjy.threadit.global.exception;

import com.petitjy.threadit.global.error.ErrorCode;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException {
	private final ErrorCode errorCode;

	public EntityNotFoundException(ErrorCode errorCode) {
		super(errorCode.getMsg());
		this.errorCode = errorCode;
	}
}
