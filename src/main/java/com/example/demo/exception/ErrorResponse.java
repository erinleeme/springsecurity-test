package com.example.demo.exception;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class ErrorResponse {
    private int code;
    private String message;
}
