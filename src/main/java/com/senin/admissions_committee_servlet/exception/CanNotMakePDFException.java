package com.senin.admissions_committee_servlet.exception;

public class CanNotMakePDFException extends RuntimeException{
    public CanNotMakePDFException(String message) {
        super(message);
    }
}
