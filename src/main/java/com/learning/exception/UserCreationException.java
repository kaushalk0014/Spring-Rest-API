package com.learning.exception;

public class UserCreationException extends RuntimeException {

	public UserCreationException() {
		super();
	}

	public UserCreationException(String erorMessage) {
		super(erorMessage);
	}

	public UserCreationException(String errorMessage, Throwable cause) {
		super(errorMessage, cause);
	}

	public UserCreationException(Throwable cause) {
		super(cause);
	}
}
