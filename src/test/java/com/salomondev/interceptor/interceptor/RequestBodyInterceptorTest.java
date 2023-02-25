package com.salomondev.interceptor.interceptor;

import com.salomondev.interceptor.interceptor.service.TraceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Type;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RequestBodyInterceptorTest {

    @Mock
    private MethodParameter methodParameter;

    @Mock
    private HttpInputMessage inputMessage;

    @Mock
    private Type targetType;

    @Mock
    private TraceService traceService;

    @InjectMocks
    private RequestBodyInterceptor interceptor;

    private Class<? extends HttpMessageConverter<?>> httpMessageConverter;

    @BeforeEach
    void setUp() {
        httpMessageConverter = (Class<? extends HttpMessageConverter<?>>) mock(HttpMessageConverter.class).getClass();
    }

    @Test
    void supports_shouldReturnTrue_whenMethodParameterHasRequestBodyAnnotation() {
        when(methodParameter.hasParameterAnnotation(RequestBody.class)).thenReturn(true);
        boolean supports = interceptor.supports(methodParameter, Object.class, httpMessageConverter);
        assertTrue(supports);
    }

    @Test
    void supports_shouldReturnFalse_whenMethodParameterDoesNotHaveRequestBodyAnnotation() {
        when(methodParameter.hasParameterAnnotation(RequestBody.class)).thenReturn(false);
        boolean supports = interceptor.supports(methodParameter, Object.class, httpMessageConverter);
        assertFalse(supports);
    }

    @Test
    void testHandleEmptyBody(){
        Object body = new Object();
        Object result = interceptor.handleEmptyBody(body, inputMessage, methodParameter, targetType, httpMessageConverter);
        assertEquals(body, result);
    }

    @Test
    void testBeforeBodyRead() throws Exception {
        HttpInputMessage result = interceptor.beforeBodyRead(inputMessage, methodParameter, targetType, httpMessageConverter);
        assertEquals(inputMessage, result);
    }

    @Test
    void testAfterBodyRead() throws Exception {
        // Arrange
        Object body = new Object();
        HttpInputMessage inputMessage = mock(HttpInputMessage.class);
        MethodParameter parameter = mock(MethodParameter.class);
        Type targetType = mock(Type.class);

        // Act
        Object result = interceptor.afterBodyRead(body, inputMessage, parameter, targetType, httpMessageConverter);

        // Assert
        assertEquals(body, result);
        verify(traceService, times(1)).registerBodyRequest(body);
    }
}
