package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UnauthorizedAccessException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnauthorizedAccessException() {
        super("You don't have permission to access this resource");
    }
    
    public UnauthorizedAccessException(String requiredRole) {
        super("Requires " + requiredRole + " role to access this resource");
    }
}