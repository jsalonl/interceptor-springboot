package com.salomondev.interceptor.interceptor;

import com.salomondev.interceptor.interceptor.service.TraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class ResponseBodyInterceptor implements ResponseBodyAdvice<Object> {

    @Autowired
    private TraceService traceService;

    /**
     * Supports is called before the controller is called
     *
     * @param returnType    MethodParameter
     * @param converterType Class<? extends HttpMessageConverter<?>>
     * @return boolean
     * @author Joan Nieto
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    /**
     * Before body is written
     *
     * @param body                  Object
     * @param returnType            MethodParameter
     * @param selectedContentType   MediaType
     * @param selectedConverterType Class<? extends HttpMessageConverter<?>>
     * @param request               ServerHttpRequest
     * @param response              ServerHttpResponse
     * @return Object
     * @author Joan Nieto
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        traceService.registerBody(body);
        return body;
    }
}
