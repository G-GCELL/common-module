package com.gabia.weat.gcellcommonmodule.error.exception;

import com.gabia.weat.gcellcommonmodule.error.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {

	private final ErrorCode errorCode;

	public CustomException(Throwable cause, ErrorCode errorCode) {
		super(cause);
		this.errorCode = errorCode;
	}

}