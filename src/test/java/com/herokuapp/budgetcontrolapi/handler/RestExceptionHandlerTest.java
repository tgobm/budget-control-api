package com.herokuapp.budgetcontrolapi.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.herokuapp.budgetcontrolapi.exception.ResourceNotFoundException;
import com.herokuapp.budgetcontrolapi.exception.ResourceNotFoundExceptionResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

class RestExceptionHandlerTest {
    @Test
    void testHandleResourceNotFoundException() {
        RestExceptionHandler restExceptionHandler = new RestExceptionHandler();
        ResourceNotFoundException ex = new ResourceNotFoundException("An error occurred");
        ResponseEntity<ResourceNotFoundExceptionResponse> actualHandleResourceNotFoundExceptionResult = restExceptionHandler
                .handleResourceNotFoundException(ex, new MockHttpServletRequest());
        assertTrue(actualHandleResourceNotFoundExceptionResult.getHeaders().isEmpty());
        assertTrue(actualHandleResourceNotFoundExceptionResult.hasBody());
        assertEquals(HttpStatus.NOT_FOUND, actualHandleResourceNotFoundExceptionResult.getStatusCode());
        ResourceNotFoundExceptionResponse body = actualHandleResourceNotFoundExceptionResult.getBody();
        assertEquals("An error occurred", body.getMessage());
        assertEquals("com.herokuapp.budgetcontrolapi.exception.ResourceNotFoundException", body.getDetails());
        assertEquals("", body.getPath());
        assertEquals(404, body.getStatus());
    }
}

