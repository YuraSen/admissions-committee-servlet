package com.senin.demo.exception;

public class AdmissionRequestNotFoundException extends RuntimeException{
    public AdmissionRequestNotFoundException(String message) {
        super(message);
    }
}
