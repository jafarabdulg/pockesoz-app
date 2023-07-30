package com.jafar.pockesoz.controller;

import com.jafar.pockesoz.model.response.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> apiException(ResponseStatusException exception) {
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .statusCode(exception.getStatus().value())
                .message(exception.getReason())
                .build();
        return ResponseEntity
                .status(exception.getStatus())
                .body(commonResponse);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> apiException(ConstraintViolationException exception) {
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage())
                .build();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(commonResponse);
    }
}
