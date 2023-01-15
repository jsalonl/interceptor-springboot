package com.salomondev.interceptor.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiRestException extends RuntimeException {
    private String code;
    private Integer status;
    private String message;
}
