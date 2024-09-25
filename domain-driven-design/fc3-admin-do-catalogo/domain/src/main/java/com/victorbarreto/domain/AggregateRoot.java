package com.victorbarreto.domain;

public class AggregateRoot<ID extends Identifier> extends Entity<ID> {

    protected AggregateRoot(final ID id) {
        super(id);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final AggregateRoot<?> that = (AggregateRoot<?>) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
