package com.eventsourcecqrs.domain.model.base;

import java.util.Objects;
import java.util.UUID;

public abstract class UniqueIdentifier {

    private UUID value;

    public UniqueIdentifier(UUID value) {
        this.value = value;
    }

    public UUID value() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UniqueIdentifier) {
            UniqueIdentifier that = (UniqueIdentifier) obj;
            return value.equals(that.value);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
