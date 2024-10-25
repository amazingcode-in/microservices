package com.amazingcode.in.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.amazingcode.in.example.response.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataDuplicateException.class)
    public ResponseEntity<ErrorResponse> handleDataDuplicateException(DataDuplicateException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
}
