package com.eventsourcecqrs.port.adapter.repository.events;

import com.eventsourcecqrs.domain.model.base.UniqueIdentifier;

import java.time.LocalDateTime;

public class DomainEventEntry {

    private UniqueIdentifier entityId;
    private String content;
    private int version;
    private String entity;
    private String event;
    private LocalDateTime createdAt;

    public DomainEventEntry(UniqueIdentifier entityId, String content, int version, String entity, String event) {
        this.entityId = entityId;
        this.content = content;
        this.version = version;
        this.entity = entity;
        this.event = event;
        this.createdAt = LocalDateTime.now();
    }

    public UniqueIdentifier getEntityId() {
        return entityId;
    }

    public String getEntity() {
        return entity;
    }

    public String getEvent() {
        return event;
    }

    public int getVersion() {
        return version;
    }

    public String getContent() {
        return content;
    }
}
