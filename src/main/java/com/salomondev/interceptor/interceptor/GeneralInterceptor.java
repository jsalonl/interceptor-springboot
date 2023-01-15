package com.salomondev.interceptor.interceptor;

import com.salomondev.interceptor.interceptor.service.TraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class GeneralInterceptor implements HandlerInterceptor {

    @Autowired
    private TraceService traceService;

    /**
     * PreHandle is called before the controller is called
     * @author Joan Nieto
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param handler Object
     * @return boolean
     * @throws Exception Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        traceService.registerInitTime(request);
        return true;
    }

    /**
     * PostHandle is called after the controller is called
     * @author Joan Nieto
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param handler Object
     * @param ex Exception
     * @throws Exception Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        traceService.registerTrace(request,response);
    }


}
