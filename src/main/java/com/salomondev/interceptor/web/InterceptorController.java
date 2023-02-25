package com.salomondev.interceptor.web;

import com.salomondev.interceptor.dto.QueryInDto;
import com.salomondev.interceptor.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*")
public class InterceptorController {

    @GetMapping(value = "/test")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseDto testGet() {
        return ResponseDto.builder().code("200").message("This is GET request").build();
    }

    @PostMapping(value = "/test")
    @ResponseStatus(value = HttpStatus.CREATED)
    public QueryInDto testPost(@RequestBody QueryInDto queryInDto) {
        return queryInDto;
    }

    @PutMapping(value = "/test")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseDto testPut(@RequestBody QueryInDto queryInDto) {
        return ResponseDto.builder().code("200").message("This is PUT request").build();
    }

    @PatchMapping(value = "/test")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseDto testPatch(@RequestBody QueryInDto queryInDto) {
        return ResponseDto.builder().code("200").message("This is PATCH request").build();
    }

    @DeleteMapping(value = "/test")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseDto testDelete(@RequestParam Long id) {
        return ResponseDto.builder().code("200").message("This is DELETE request with id: "+id).build();
    }
}
