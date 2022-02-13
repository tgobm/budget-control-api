package com.herokuapp.budgetcontrolapi.handler;

import com.herokuapp.budgetcontrolapi.exception.ExceptionResponse;
import com.herokuapp.budgetcontrolapi.exception.ResourceNotFoundException;
import com.herokuapp.budgetcontrolapi.exception.ResourceNotFoundExceptionResponse;
import com.herokuapp.budgetcontrolapi.exception.ValidationExceptionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.herokuapp.budgetcontrolapi.util.ErrorMessage.FIELD_VALIDATION_FAILED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, String> listFieldErrors = new HashMap<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        fieldErrors.forEach(error -> listFieldErrors.put(error.getField(), error.getDefaultMessage()));

        return new ResponseEntity<>(ValidationExceptionResponse.builder() //
                .timeStamp(LocalDateTime.now()) //
                .status(status.value()) //
                .message(FIELD_VALIDATION_FAILED.getMessage()) //
                .path(((ServletWebRequest) request).getRequest().getRequestURI()) //
                .details(ex.getClass().getName()) //
                .fieldErrors(listFieldErrors)
                .build(), status);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(ExceptionResponse.builder() //
                .timeStamp(LocalDateTime.now()) //
                .status(status.value()) //
                .message(ex.getMessage()) //
                .path(((ServletWebRequest) request).getRequest().getRequestURI()) //
                .details(ex.getClass().getName()) //
                .build(), headers, status);
    }
}