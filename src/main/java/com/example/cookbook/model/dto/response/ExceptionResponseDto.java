package com.example.cookbook.model.dto.response;

import lombok.Data;

@Data
public class ExceptionResponseDto {
    private String timestamp;
    private int status;
    private String error;
    private String message;
}
