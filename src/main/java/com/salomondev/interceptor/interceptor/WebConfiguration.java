package com.salomondev.interceptor.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    private GeneralInterceptor generalInterceptor;

    @Value("${spring.application.interceptor.enabled}")
    private boolean interceptorEnabled;

    /**
     * Add interceptors
     * @author Joan Nieto
     * @param registry InterceptorRegistry
     */
    @Override
    public void addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry registry) {
        if (interceptorEnabled) {
            registry.addInterceptor(generalInterceptor);
        }
    }
}
