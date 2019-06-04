package com.eventsourcecqrs.domain.model.base;

import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

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
            Method method = getEventApplyMethod(eventClass, entityStateClass);
            method.setAccessible(true);
            method.invoke(this, domainEvent);
            method.setAccessible(false);
        } catch (Exception e) {
            throw new IllegalArgumentException(MessageFormat.format("There was an error applying the domain event to the {0} entity.",
                    domainEvent.getClass().getSimpleName()), e);
        }
    }

    private Method getEventApplyMethod(Class<?> eventClass, Class<?> entityStateClass) {
        return Stream.of(entityStateClass.getDeclaredMethods())
                .filter(method -> method.getParameterCount() == 1 && method.getParameterTypes()[0] == eventClass)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(
                        MessageFormat.format("It could not find a method with parameter {0} in {1}.",
                                entityStateClass.getSimpleName(), eventClass.getSimpleName())));
    }
}
