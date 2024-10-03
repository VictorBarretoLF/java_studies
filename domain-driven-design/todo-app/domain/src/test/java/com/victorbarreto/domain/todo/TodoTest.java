package com.victorbarreto.domain.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {


    @Test
    public void givenValidParams_whenCallNewTodo_thenInstantiateATodo() {
        final var expectedTitle = "Estudar";
        final var expectedDescription = "Estudar para a certificação";
        final var expectedIsCompleted = true;

        final var actualTodo = Todo.newTodo(
                expectedTitle,
                expectedDescription,
                expectedIsCompleted
        );

        Assertions.assertNotNull(actualTodo);
        Assertions.assertNotNull(actualTodo.getId());
        Assertions.assertEquals(expectedTitle, actualTodo.getTitle());
        Assertions.assertEquals(expectedDescription, actualTodo.getDescription());
        Assertions.assertEquals(expectedIsCompleted, actualTodo.isCompleted());
        Assertions.assertNotNull(actualTodo.getCreatedAt());
        Assertions.assertNotNull(actualTodo.getUpdatedAt());
        Assertions.assertNull(actualTodo.getDeletedAt());
    }

}