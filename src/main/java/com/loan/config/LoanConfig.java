package com.loan.config;

import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoanConfig {
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer(){
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5176","http://localhost:5175","https://smart-loan-admin.vercel.app")
                        .allowedHeaders("Content-Type","Authorization")
                        .allowedMethods("GET","POST","PUT","DELETE","UPDATE")
                        .allowCredentials(true);
            }
        };

    }

}
