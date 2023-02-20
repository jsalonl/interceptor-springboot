package com.salomondev.interceptor.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QueryInDto {
    private Long id;
    private String name;
    private String lastName;
    private Long documentNumber;
}
