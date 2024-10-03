package com.victorbarreto.domain.todo;

import java.time.Instant;
import java.util.UUID;

public class Todo {

    private String id;
    private String title;
    private String description;
    private boolean completed;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private Todo(
            final String anId,
            final String aTitle,
            final String aDescription,
            final boolean isCompleted,
            final Instant aCreationDate,
            final Instant aUpdateDate,
            final Instant aDeleteDate) {
        this.id = anId;
        this.title = aTitle;
        this.description = aDescription;
        this.completed = isCompleted;
        this.createdAt = aCreationDate;
        this.updatedAt = aUpdateDate;
        this.deletedAt = aDeleteDate;
    }

    public static Todo newTodo(
            final String aTitle,
            final String aDescription,
            final boolean isCompleted) {
        final var id = UUID.randomUUID().toString();
        final var now = Instant.now();
        return new Todo(id, aTitle, aDescription, isCompleted, now, now, null);
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", completed=" + completed +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", deletedAt=" + deletedAt +
                '}';
    }

}
