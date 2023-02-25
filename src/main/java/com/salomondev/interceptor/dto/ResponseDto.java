package com.salomondev.interceptor.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.AllArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
public class ResponseDto {
    private String code;
    private String message;
}
