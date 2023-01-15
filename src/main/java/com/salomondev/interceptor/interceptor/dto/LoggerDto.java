package com.salomondev.interceptor.interceptor.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Map;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoggerDto {
    private String method;
    private String ip;
    private String path;
    private Map<String, Object> parametersIn;
    private Object parametersOut;
    private Integer codeResponse;
    @JsonIgnore
    private Long timeConsumeService;
    private String timeResponseService;

    private static LoggerDto log;

    public static synchronized LoggerDto getInstance(){
        if(log == null){
            log = new LoggerDto();
        }
        return log;
    }
}
