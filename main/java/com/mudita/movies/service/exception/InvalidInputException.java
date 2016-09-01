package com.mudita.movies.service.exception;

@SuppressWarnings("serial")
public class InvalidInputException extends MovieException {

	public InvalidInputException(String message, Throwable throwable) {
		super(ErrorCode.INVALID_INPUT, message, throwable);
	}
	
	public InvalidInputException(String message) {
		super(ErrorCode.INVALID_INPUT, message);
	}

}
