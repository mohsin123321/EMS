package com.example.springframework.exception;

public class EmailAlreadyExistsException  extends RuntimeException {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
