package com.springbootstudies.spring_boot_springdoc2.errors.exceptions;

import com.springbootstudies.spring_boot_springdoc2.errors.ErrorCode;

public class ResourceNotFoundException extends RuntimeException {
    private ErrorCode errorCode = null;

    public ResourceNotFoundException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}