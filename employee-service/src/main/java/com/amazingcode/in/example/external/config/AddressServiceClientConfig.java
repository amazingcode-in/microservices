package com.amazingcode.in.example.external.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazingcode.in.example.external.exception.CustomErrorDecoder;

import feign.codec.ErrorDecoder;

@Configuration
public class AddressServiceClientConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }
}
