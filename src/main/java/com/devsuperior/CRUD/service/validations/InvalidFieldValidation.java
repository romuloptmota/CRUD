package com.devsuperior.CRUD.service.validations;

public class InvalidFieldValidation extends RuntimeException {

    public InvalidFieldValidation(String message) {
        super(message);
    }
}
