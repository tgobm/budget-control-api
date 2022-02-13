package com.herokuapp.budgetcontrolapi.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@SuperBuilder
@Getter
@EqualsAndHashCode(callSuper = false)
public class ValidationExceptionResponse extends ExceptionResponse {

    private Map<String, String> fieldErrors;
}