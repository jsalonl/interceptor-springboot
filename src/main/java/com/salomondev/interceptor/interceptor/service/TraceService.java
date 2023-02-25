package com.salomondev.interceptor.interceptor.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface TraceService {
    void registerBody(Object body);
    void registerBodyRequest(Object body);
    void registerInitTime(HttpServletRequest request);
    void registerTrace(HttpServletRequest request, HttpServletResponse response);
}
