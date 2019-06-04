package com.eventsourcecqrs.domain.model.base;

public abstract class DomainEventNotifier {

    private static DomainEventNotifier currentDomainEventNotifier;

    public DomainEventNotifier() {
    }

    public abstract <E extends DomainEvent> void notify(E event);

    public static DomainEventNotifier currentNotifier() {
        return currentDomainEventNotifier;
    }

    public static void setCurrentDomainEventNotifier(DomainEventNotifier currentDomainEventNotifier) {
        DomainEventNotifier.currentDomainEventNotifier = currentDomainEventNotifier;
    }
}
