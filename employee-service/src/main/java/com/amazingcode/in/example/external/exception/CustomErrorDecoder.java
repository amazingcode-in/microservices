package com.amazingcode.in.example.external.exception;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
// import org.springframework.web.client.RestClientException;

import com.amazingcode.in.example.exception.DataDuplicateException;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new ErrorDecoder.Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == HttpStatus.CONFLICT.value()) {
            return new DataDuplicateException("Address already exists.");
        }
        return defaultErrorDecoder.decode(methodKey, response);
    }
}
