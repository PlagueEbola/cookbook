package com.example.cookbook.controller;

import com.example.cookbook.model.dto.response.ExceptionResponseDto;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RuntimeExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponseDto> handleException(RuntimeException exception) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto();
        responseDto.setStatus(HttpStatus.BAD_GATEWAY.value());
        responseDto.setError(HttpStatus.BAD_REQUEST.name());
        responseDto.setTimestamp(LocalDateTime.now().toString());
        responseDto.setMessage(exception.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_GATEWAY);
    }
}
