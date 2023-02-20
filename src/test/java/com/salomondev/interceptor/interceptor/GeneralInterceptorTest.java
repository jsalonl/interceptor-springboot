package com.salomondev.interceptor.interceptor;

import com.salomondev.interceptor.interceptor.service.TraceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GeneralInterceptorTest {

    @Mock
    private TraceService traceService;

    @InjectMocks
    private GeneralInterceptor generalInterceptor;

    private HttpServletRequest request;
    private HttpServletResponse response;
    private Object handler;

    @BeforeEach
    void setUp() {
        request = null;
        response = null;
        handler = null;
    }

    @Test
    void preHandle() {
        boolean preHandle = generalInterceptor.preHandle(request, response, handler);
        assertTrue(preHandle);
    }

    @Test
    void afterCompletion() {
        generalInterceptor.afterCompletion(request, response, handler, null);
        verify(traceService).registerTrace(request, response);
    }
}