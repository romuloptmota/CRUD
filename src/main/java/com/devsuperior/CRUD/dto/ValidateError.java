package com.devsuperior.CRUD.dto;



import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidateError extends CustomError {

    public ValidateError(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }


    public List<FieldMessage> fieldErrors = new ArrayList<>();

    public void addError(String fieldName, String message) {
        fieldErrors.add(new FieldMessage(fieldName, message));
    }

    public List<FieldMessage> getFieldErrors() {
        return fieldErrors;
    }
}
