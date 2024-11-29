package com.victorbarreto.domain.exceptions;

import com.victorbarreto.domain.validation.Error;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DomainExceptionTest {

    @Test
    void givenOneError_whenCreateDomainException_thenDomainExceptionIsCreated() {
        final var message = "message";
        final var error = new Error(message);
        final var domainException = DomainException.with(error);
    }

}