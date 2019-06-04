package com.eventsourcecqrs.domain.model.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class Entity<I extends UniqueIdentifier, E extends EntityState> {

    private List<DomainEvent> appliedEvents = new ArrayList<>();

    public List<DomainEvent> changes() {
        return Collections.unmodifiableList(appliedEvents);
    }

    protected void applyEvent(DomainEvent event) {
        currentState().update(event);
        appliedEvents.add(event);
        DomainEventNotifier.currentNotifier().notify(event);
    }

    public abstract E currentState();

    public I id() {
        return (I) currentState().id();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Entity) {
            Entity that = (Entity) obj;
            return id().equals(that.id());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id());
    }
}
