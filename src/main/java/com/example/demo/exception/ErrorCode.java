package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_INPUT_PARAMETER(700, "잘못된 파라미터 값입니다."),
    INVALID_DATATYPE(701, "잘못된 데이터 타입입니다."),

    NOT_FOUND_CATEGORY(900,"유효하지 않은 카테고리입니다."),
    NOT_FOUND_EMAIL(900,"유효하지 않는 Email입니다."),
    NOT_FOUND_MEETING(900,"유효하지 않는 소모임입니다."),

    VALUE_ALREADY_EXISTS(901, "이미 존재하는 데이터입니다."),
    IS_EXIST_USER_BY_EMAIL(901, "중복된 이메일입니다.");

    private final int code;
    private final String message;

}
