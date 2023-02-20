package com.salomondev.interceptor.interceptor.service;

import com.salomondev.interceptor.interceptor.dto.LoggerDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class TraceServiceImpl implements TraceService {

    ObjectMapper om = new ObjectMapper();

    LoggerDto loggerDto = LoggerDto.getInstance();

    /**
     * Register the body of the response
     *
     * @param body Object
     * @author Joan Nieto
     */
    @Override
    public void registerBody(Object body) {
        loggerDto.setParametersOut(body);
    }

    /**
     * Register the body of the request
     *
     * @param body Object
     * @author Joan Nieto
     */
    @Override
    public void registerBodyRequest(Object body) {
        Map<String, Object> map = new HashMap<>();
        map.put("body", body);
        loggerDto.setParametersIn(map);
    }

    /**
     * Register the initial time of the request and the parameters of the request
     *
     * @param request HttpServletRequest
     * @author Joan Nieto
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
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @author Joan Nieto
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
            log.info("LOG{}", message);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    /**
     * Format the parameters of the request to a Map type
     *
     * @param requestParameters Map<String, String[]>
     * @return Map<String, Object>
     * @author Joan Nieto
     */
    private Map<String, Object> formatParameters(Map<String, String[]> requestParameters) {
        Map<String, Object> parameters = new HashMap<>();
        Map<String, Object> parametersIn = new HashMap<>();
        for (Map.Entry<String, String[]> entry : requestParameters.entrySet()) {
            parameters.put(entry.getKey(), entry.getValue()[0]);
        }
        parametersIn.put("query", parameters);
        return parametersIn;
    }
}
