package com.herokuapp.budgetcontrolapi.util;

import lombok.Getter;

@Getter
public enum ErrorMessage {

    RESOURCE_NOT_FOUND("RESOURCE_NOT_FOUND"),
    FIELD_VALIDATION_FAILED("FIELD_VALIDATION_FAILED");
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }
}

