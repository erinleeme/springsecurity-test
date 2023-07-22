package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtException extends RuntimeException{
    private final ErrorCode errorCode;
}
