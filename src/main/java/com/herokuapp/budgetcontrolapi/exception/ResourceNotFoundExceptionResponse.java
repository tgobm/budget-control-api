package com.herokuapp.budgetcontrolapi.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class ResourceNotFoundExceptionResponse extends ExceptionResponse {
}