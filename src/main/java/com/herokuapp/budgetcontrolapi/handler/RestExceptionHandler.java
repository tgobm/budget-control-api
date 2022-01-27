package com.herokuapp.budgetcontrolapi.handler;

import com.herokuapp.budgetcontrolapi.exception.ResourceNotFoundException;
import com.herokuapp.budgetcontrolapi.exception.ResourceNotFoundExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResourceNotFoundExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        return new ResponseEntity<>(ResourceNotFoundExceptionResponse.builder() //
                .timeStamp(LocalDateTime.now()) //
                .status(NOT_FOUND.value()) //
                .message(ex.getMessage()) //
                .path(request.getRequestURI()) //
                .details(ex.getClass().getName()) //
                .build(), NOT_FOUND);
    }
}
