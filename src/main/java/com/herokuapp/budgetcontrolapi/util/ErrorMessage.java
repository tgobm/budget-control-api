package com.herokuapp.budgetcontrolapi.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {

    RESOURCE_NOT_FOUND("RESOURCE_NOT_FOUND"),
    FIELD_VALIDATION_FAILED("FIELD_VALIDATION_FAILED");

    private final String message;
}

