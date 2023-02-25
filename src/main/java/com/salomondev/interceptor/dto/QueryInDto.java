package com.salomondev.interceptor.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.AllArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
public class QueryInDto {
    private Long id;
    private String name;
    private String lastName;
    private Long documentNumber;
}
