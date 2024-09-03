package com.victorbarreto.application;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class UseCaseTest {

    @Test
    void testExecute() {
        // Arrange, Act and Assert
        assertNotNull(new UseCase());
        assertNotNull(new UseCase().execute());
    }

}
