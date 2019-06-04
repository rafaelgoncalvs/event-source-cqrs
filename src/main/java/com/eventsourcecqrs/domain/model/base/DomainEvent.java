package com.eventsourcecqrs.domain.model.base;

import java.time.LocalDateTime;

public abstract class DomainEvent {

    private LocalDateTime createdAt;

    public DomainEvent() {
        this.createdAt = LocalDateTime.now();
    }
}
