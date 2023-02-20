package com.salomondev.interceptor.exception;

import lombok.*;

@Getter
@AllArgsConstructor
public class ApiRestException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final String code;
    private final Integer status;
    private final String message;
}
