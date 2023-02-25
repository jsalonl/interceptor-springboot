package com.salomondev.interceptor.interceptor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WebConfigurationTest {

    @InjectMocks
    WebConfiguration webConfiguration;

    @Test
    void testAddInterceptors() {
        InterceptorRegistry interceptorRegistry = mock(InterceptorRegistry.class);
        ReflectionTestUtils.setField(webConfiguration, "generalInterceptor", new GeneralInterceptor());
        ReflectionTestUtils.setField(webConfiguration, "interceptorEnabled", true);
        webConfiguration.addInterceptors(interceptorRegistry);
        verify(interceptorRegistry).addInterceptor(any(GeneralInterceptor.class));
    }
}