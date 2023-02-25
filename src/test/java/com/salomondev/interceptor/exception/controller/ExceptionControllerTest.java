package com.salomondev.interceptor.exception.controller;

import com.salomondev.interceptor.exception.ApiRestException;
import com.salomondev.interceptor.exception.dto.ErrorDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExceptionControllerTest {

    @InjectMocks
    private ExceptionController exceptionController;


    @Test
    void handleGeneralException_returnsErrorDto() {
        String code = "404";
        String message = "Resource not found";
        ApiRestException ex = new ApiRestException(code, 404, message);
        ResponseEntity<ErrorDto> response = exceptionController.handleGeneralException(ex);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assert response.getBody() != null;
        assertEquals(code, response.getBody().getCode());
        assertEquals(message, response.getBody().getMessage());
    }

    @Test
    void handleMissingParams_returnsErrorDto() {
        String parameterName = "foo";
        MissingServletRequestParameterException ex = new MissingServletRequestParameterException(parameterName, "String");
        ResponseEntity<ErrorDto> response = exceptionController.handleMissingParams(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assert response.getBody() != null;
        assertEquals("400", response.getBody().getCode());
        assertEquals("Missing parameter: " + parameterName, response.getBody().getMessage());
    }

    @Test
    void handleException_returnsInternalServerError() {
        String message = "Something went wrong";
        Exception ex = new Exception(message);
        ResponseEntity<ErrorDto> response = exceptionController.handleException(ex);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assert response.getBody() != null;
        assertEquals("500", response.getBody().getCode());
        assertEquals(message, response.getBody().getMessage());
    }
}