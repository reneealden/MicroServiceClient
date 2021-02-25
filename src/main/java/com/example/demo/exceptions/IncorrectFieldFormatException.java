package com.example.demo.exceptions;

public class IncorrectFieldFormatException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IncorrectFieldFormatException(String message) {
        super(message);
    }
}

