package com.springbootstudies.spring_boot_springdoc2.errors;

import lombok.Getter;

@Getter
public enum ErrorCode {
    BOOK_NOT_FOUND("ERR001"),
    ;

    private final String code;

    ErrorCode(String code) {
        this.code = code;
    }

}
