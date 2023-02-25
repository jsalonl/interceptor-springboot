package com.salomondev.interceptor.exception.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.AllArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
public class ErrorDto {
    private String code;
    private String message;
}
