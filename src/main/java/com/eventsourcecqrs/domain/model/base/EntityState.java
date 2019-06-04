package com.eventsourcecqrs.domain.model.base;

import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;

public abstract class EntityState<I extends UniqueIdentifier> {

    protected LocalDateTime createdAt;

    public EntityState(List<DomainEvent> events) {
        events.forEach(this::update);
    }

    public abstract I id();

    void update(DomainEvent domainEvent) {
        try {
            Class<?> eventClass = domainEvent.getClass();
            Class<?> entityStateClass = Class.forName(this.getClass().getName());
            Method method = entityStateClass.getDeclaredMethod("when", eventClass);
            method.setAccessible(true);
            method.invoke(this, domainEvent);
            method.setAccessible(false);
        } catch (Exception e) {
            throw new IllegalArgumentException(MessageFormat.format("There was an error applying the domain event to the {0} entity.",
                    domainEvent.getClass().getSimpleName()), e);
        }
    }
}
