package com.learning.enception;

public class ResourceNotFoundException extends RuntimeException{

	public ResourceNotFoundException() {
			super();
	}
	public ResourceNotFoundException(String erorMessage) {
		super(erorMessage);
	}
	
	public ResourceNotFoundException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }
}
