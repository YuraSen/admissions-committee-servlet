package com.senin.demo.utils.validation;


public class FieldValidator {

    public boolean validate(final String hex) {

        return hex == null || hex.isEmpty();
    }
}
