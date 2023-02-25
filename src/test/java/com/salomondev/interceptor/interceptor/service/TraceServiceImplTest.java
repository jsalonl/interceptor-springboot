package com.salomondev.interceptor.interceptor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salomondev.interceptor.interceptor.dto.LoggerDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TraceServiceImplTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private TraceServiceImpl traceService;

    @Mock
    private ObjectMapper om = new ObjectMapper();

    private LoggerDto loggerDto;

    private final Object body = new Object();

    private final Map<String, String[]> map = new HashMap<>();

    @BeforeEach
    void setUp() {
        loggerDto = LoggerDto.builder()
                .codeResponse(200)
                .method("GET")
                .path("/test")
                .timeConsumeService(1000L)
                .ip("127.0.0.1")
                .parametersIn(new HashMap<>())
                .parametersOut(new HashMap<>())
                .timeResponseService("1000")
                .build();

        map.put("test", new String[]{"test"});

    }

    @Test
    void registerBody() {
        traceService.registerBody(body);
        assertNotNull(loggerDto.getParametersOut());
    }

    @Test
    void registerBodyRequest() {
        traceService.registerBodyRequest(body);
        assertNotNull(loggerDto.getParametersIn());
    }

    @Test
    void registerInitTime() {
        when(request.getParameterMap()).thenReturn(map);
        traceService.registerInitTime(request);
        assertNotNull(loggerDto.getParametersIn());
    }

    @Test
    void registerTrace() throws JsonProcessingException {
        when(request.getParameterMap()).thenReturn(map);
        traceService.registerInitTime(request);
        traceService.registerBodyRequest(body);
        traceService.registerBody(body);
        traceService.registerTrace(request, response);
        assertNotNull(loggerDto.getParametersIn());
        assertNotNull(loggerDto.getCodeResponse());
        assertNotNull(loggerDto.getMethod());
        assertNotNull(loggerDto.getIp());
        assertNotNull(loggerDto.getPath());
        assertNotNull(loggerDto.getTimeConsumeService());
        assertNotNull(loggerDto.getTimeResponseService());
        verify(om).writeValueAsString(traceService.loggerDto);
    }

    @Test
    void registerTrace_Exception() throws JsonProcessingException {
        when(request.getParameterMap()).thenReturn(map);
        traceService.registerInitTime(request);
        traceService.registerBodyRequest(body);
        traceService.registerBody(body);
        doThrow(JsonProcessingException.class).when(om).writeValueAsString(traceService.loggerDto);
        traceService.registerTrace(request, response);
        assertNotNull(loggerDto.getParametersIn());
        verify(om).writeValueAsString(traceService.loggerDto);
    }
}