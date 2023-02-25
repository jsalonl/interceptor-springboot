package com.salomondev.interceptor.web;

import com.salomondev.interceptor.dto.QueryInDto;
import com.salomondev.interceptor.dto.ResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class InterceptorControllerTest {

    @InjectMocks
    private InterceptorController controller;

    ResponseDto responseDto;
    QueryInDto queryInDto;

    @Test
    void testGet() {
        responseDto = ResponseDto.builder()
                .code("200")
                .message("This is GET request")
                .build();
        ResponseDto response = controller.testGet();
        assertEquals(responseDto.getCode(), response.getCode());
        assertEquals(responseDto.getMessage(), response.getMessage());
    }

    @Test
    void testPost() {
        responseDto = ResponseDto.builder().code("201").build();
        queryInDto = QueryInDto.builder()
                .id(1L)
                .documentNumber(1L)
                .name("test")
                .lastName("test")
                .build();
        QueryInDto response = controller.testPost(queryInDto);
        assertEquals(queryInDto.getId(), response.getId());
        assertEquals(queryInDto.getDocumentNumber(), response.getDocumentNumber());
        assertEquals(queryInDto.getName(), response.getName());
        assertEquals(queryInDto.getLastName(), response.getLastName());
    }

    @Test
    void testPut() {
        responseDto = ResponseDto.builder().code("200").build();
        queryInDto = QueryInDto.builder().id(1L).build();
        ResponseDto response = controller.testPut(queryInDto);
        assertEquals(responseDto.getCode(), response.getCode());
    }

    @Test
    void testPatch() {
        responseDto = ResponseDto.builder().code("200").build();
        queryInDto = QueryInDto.builder().id(1L).build();
        ResponseDto response = controller.testPatch(queryInDto);
        assertEquals(responseDto.getCode(), response.getCode());
    }

    @Test
    void testDelete() {
        responseDto = ResponseDto.builder().code("200").build();
        ResponseDto response = controller.testDelete(1L);
        assertEquals(responseDto.getCode(), response.getCode());
    }
}