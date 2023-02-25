package com.salomondev.interceptor.interceptor;

import com.salomondev.interceptor.interceptor.service.TraceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.NativeWebRequest;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ResponseBodyInterceptorTest {

    @Mock
    private MethodParameter methodParameter;

    @Mock
    private HttpInputMessage inputMessage;

    @Mock
    private Type targetType;

    @Mock
    private TraceService traceService;

    @Mock
    private NativeWebRequest webRequest;

    @Mock
    private HttpMessageConverter<Object> converter;

    private ServerHttpRequest request;
    private ServerHttpResponse response;

    @InjectMocks
    private ResponseBodyInterceptor responseBodyInterceptor;

    private Class<? extends HttpMessageConverter<?>> httpMessageConverter;

    @BeforeEach
    void setUp() {
        httpMessageConverter = (Class<? extends HttpMessageConverter<?>>) mock(HttpMessageConverter.class).getClass();
        request = new ServletServerHttpRequest(new MockHttpServletRequest());
        response = new ServletServerHttpResponse(new MockHttpServletResponse());
    }

    @Test
    void supports() {
        boolean supports = responseBodyInterceptor.supports(methodParameter, httpMessageConverter);
        assertTrue(supports);
    }

    @Test
    void beforeBodyWrite() {
        Object body = new Object();
        MediaType contentType = MediaType.APPLICATION_JSON;
        Object result = responseBodyInterceptor.beforeBodyWrite(body, methodParameter, contentType, (Class<? extends HttpMessageConverter<?>>) converter.getClass(), request, response);
        assertEquals(body, result);
        verify(traceService, times(1)).registerBody(body);
    }
}