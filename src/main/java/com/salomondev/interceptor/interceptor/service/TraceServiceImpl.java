package com.salomondev.interceptor.interceptor.service;

import com.salomondev.interceptor.interceptor.dto.LoggerDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TraceServiceImpl implements TraceService {

    Logger logger = LoggerFactory.getLogger("LOG Interceptor");

    ObjectMapper om = new ObjectMapper();

    LoggerDto loggerDto = LoggerDto.getInstance();

    /**
     * Register the body of the response
     * @author Joan Nieto
     * @param body Object
     */
    @Override
    public void registerBody(Object body) {
        loggerDto.setParametersOut(body);
    }

    /**
     * Register the body of the request
     * @author Joan Nieto
     * @param body Object
     */
    @Override
    public void registerBodyRequest(Object body) {
        Map<String, Object> map = om.convertValue(body, Map.class);
        logger.info("Body Request: {}", map);
        loggerDto.setParametersIn(map);
    }

    /**
     * Register the initial time of the request and the parameters of the request
     * @author Joan Nieto
     * @param request HttpServletRequest
     */
    @Override
    public void registerInitTime(HttpServletRequest request) {
        loggerDto.setTimeConsumeService(new Date().getTime());
        if (request.getParameterMap().size() > 0) {
            loggerDto.setParametersIn(formatParameters(request.getParameterMap()));
        }
        loggerDto.setParametersIn(formatParameters(request.getParameterMap()));
    }

    /**
     * Register the trace of the request
     * @author Joan Nieto
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @Override
    public void registerTrace(HttpServletRequest request, HttpServletResponse response) {
        String timeResponse = (new Date().getTime() - loggerDto.getTimeConsumeService()) + " ms";
        loggerDto.setIp(request.getRemoteAddr());
        loggerDto.setMethod(request.getMethod());
        loggerDto.setPath(request.getRequestURI());
        loggerDto.setCodeResponse(response.getStatus());
        loggerDto.setTimeResponseService(timeResponse);
        try {
            String message = om.writeValueAsString(loggerDto);
            logger.info("LOG{}", message);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }

    /**
     * Format the parameters of the request to a Map type
     * @author Joan Nieto
     * @param requestParameters Map<String, String[]>
     * @return Map<String, Object>
     */
    private Map<String, Object> formatParameters(Map<String, String[]> requestParameters) {
        Map<String, Object> parameters = new HashMap<>();
        for (Map.Entry<String, String[]> entry : requestParameters.entrySet()) {
            parameters.put(entry.getKey(), entry.getValue()[0]);
        }
        return parameters;
    }
}
