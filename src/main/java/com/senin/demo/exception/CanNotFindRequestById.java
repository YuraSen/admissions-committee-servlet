package com.senin.demo.exception;

public class CanNotFindRequestById extends RuntimeException{
    public CanNotFindRequestById(String message) {
        super(message);
    }
}
