package com.salomondev.interceptor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryInDto {
    private Long id;
    private String name;
    private String lastName;
    private Long documentNumber;
}
