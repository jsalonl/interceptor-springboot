package com.salomondev.interceptor;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InterceptorApplicationTests {

    @Test
    void contextLoads() {
        InterceptorApplication.main(new String[] {});
        assertTrue(true);
    }

}