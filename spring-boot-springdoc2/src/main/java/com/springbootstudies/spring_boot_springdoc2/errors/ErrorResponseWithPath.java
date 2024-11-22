package com.springbootstudies.spring_boot_springdoc2.errors;

import lombok.*;

@Getter
@RequiredArgsConstructor
public class ErrorResponseWithPath {

    private final String path;
    private final String message;

}
