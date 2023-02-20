package com.salomondev.interceptor.interceptor;

import com.salomondev.interceptor.interceptor.service.TraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.lang.reflect.Type;

@ControllerAdvice
public class RequestBodyInterceptor implements RequestBodyAdvice {

    @Autowired
    private TraceService traceService;

    /**
     * Supports is called before the controller is called
     *
     * @param methodParameter MethodParameter
     * @param targetType      Type
     * @param converterType   Class<? extends HttpMessageConverter<?>>
     * @return boolean
     * @author Joan Nieto
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return methodParameter.hasParameterAnnotation(RequestBody.class);
    }

    /**
     * Handle empty body
     *
     * @param inputMessage  HttpInputMessage
     * @param parameter     MethodParameter
     * @param targetType    Type
     * @param converterType Class<? extends HttpMessageConverter<?>>
     * @return HttpInputMessage
     * @author Joan Nieto
     */
    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    /**
     * Before body is read
     *
     * @param inputMessage  HttpInputMessage
     * @param parameter     MethodParameter
     * @param targetType    Type
     * @param converterType Class<? extends HttpMessageConverter<?>>
     * @return HttpInputMessage
     * @author Joan Nieto
     */
    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType){
        return inputMessage;
    }

    /**
     * After body is read
     *
     * @param body          Object
     * @param inputMessage  HttpInputMessage
     * @param parameter     MethodParameter
     * @param targetType    Type
     * @param converterType Class<? extends HttpMessageConverter<?>>
     * @return Object
     * @author Joan Nieto
     */
    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        traceService.registerBodyRequest(body);
        return body;
    }
}
