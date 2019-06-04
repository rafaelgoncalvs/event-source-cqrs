package com.eventsourcecqrs.config;

import com.eventsourcecqrs.domain.model.base.DomainEvent;
import com.eventsourcecqrs.domain.model.base.DomainEventHandler;
import com.eventsourcecqrs.domain.model.base.DomainEventNotifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SpringDomainEventNotifier extends DomainEventNotifier {

    private ApplicationContext applicationContext;

    public SpringDomainEventNotifier(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public <E extends DomainEvent> void notify(E event) {
        String[] beans = applicationContext.getBeanNamesForType(DomainEventHandler.class);
        List<Object> domainEventHandlers = Stream.of(beans).map(bean -> applicationContext.getBean(bean)).collect(Collectors.toList());
        List<DomainEventHandler<E>> domainEventHandlersThatObserveTheEvent = getDomainEventHandlersThatObserveEvent(event, domainEventHandlers);
        domainEventHandlersThatObserveTheEvent.forEach(domainEventHandler -> domainEventHandler.handle(event));
    }

    private <E extends DomainEvent> List<DomainEventHandler<E>> getDomainEventHandlersThatObserveEvent(E domainEvent, List<Object> domainEventHandlers) {
        return domainEventHandlers.stream()
                .filter(domainEventHandler -> isEventBeingObservedByDomainEventHandler((DomainEventHandler<?>) domainEventHandler, domainEvent))
                .map(domainEventHandler -> (DomainEventHandler<E>) domainEventHandler)
                .collect(Collectors.toList());
    }

    private <E extends DomainEvent> boolean isEventBeingObservedByDomainEventHandler(DomainEventHandler<?> domainEventHandler, E domainEvent) {
        try {
            Class domainEventHandlerClass = domainEventHandler.getClass();
            if(isDomainEventHandlerAProxy(domainEventHandler)) {
                domainEventHandlerClass = Class.forName(domainEventHandler.getClass().getGenericSuperclass().getTypeName());
            }
            for(Type interfaceImplementedByTheDomainEventHandler : domainEventHandlerClass.getGenericInterfaces()) {
                if(interfaceImplementedByTheDomainEventHandler instanceof ParameterizedType) {
                    return isDomainEventClassAGenericTypeOfInterface(interfaceImplementedByTheDomainEventHandler, domainEvent);
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Could not find any domain event handler for the event: " + domainEvent.getClass().getTypeName());
        }
        return false;
    }

    private <E extends DomainEvent> boolean isDomainEventClassAGenericTypeOfInterface(Type interfaceImplementedByTheDomainEventHandler, E domainEvent) {
        ParameterizedType parameterizedType = (ParameterizedType) interfaceImplementedByTheDomainEventHandler;
        return Stream.of(parameterizedType.getActualTypeArguments())
                .map(genericTypeClass -> (Class<?>) genericTypeClass)
                .anyMatch(domainEventClass -> domainEventClass.isInstance(domainEvent));
    }

    private boolean isDomainEventHandlerAProxy(DomainEventHandler<?> domainEventHandler) {
        return domainEventHandler.getClass().getTypeName().contains("CGLIB");
    }
}
