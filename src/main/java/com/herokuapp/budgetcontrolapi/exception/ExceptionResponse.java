package com.herokuapp.budgetcontrolapi.exception;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@Data
public class ExceptionResponse {

    protected LocalDateTime timeStamp;

    protected int status;

    protected String message;

    protected String path;

    protected String details;
}