package com.victorbarreto.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    void categoryIdShouldNotBeNull() {
        Category category = new Category();
        assertNotNull(category);
    }

}