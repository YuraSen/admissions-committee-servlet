package com.senin.demo.exception;

public class DbProcessingException extends RuntimeException {
    public DbProcessingException(String message) {
        super(message);
    }
}
