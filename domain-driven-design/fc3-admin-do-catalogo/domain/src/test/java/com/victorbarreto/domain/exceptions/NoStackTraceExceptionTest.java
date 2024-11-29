package com.victorbarreto.domain.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoStackTraceExceptionTest {

    @Test
    void givenMessage_whenCreateNoStackTraceException_thenNoStackTraceExceptionIsCreated() {
        final var message = "message";
        final var noStackTraceException = new NoStackTraceException(message);
        assertEquals(message, noStackTraceException.getMessage());
        assertNull(noStackTraceException.getCause());
    }

}