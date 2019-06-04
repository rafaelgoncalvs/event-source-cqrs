package com.eventsourcecqrs.application.command.base;

import com.eventsourcecqrs.domain.model.base.Entity;
import com.eventsourcecqrs.domain.model.base.EventStore;
import com.eventsourcecqrs.domain.model.base.EventStream;
import com.eventsourcecqrs.domain.model.base.UniqueIdentifier;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.text.MessageFormat;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public abstract class CommandHandler<E extends Entity> {

    protected final EventStore eventStore;

    protected CommandHandler(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    public ReturnCommandHandler handle(Command command) {
        Object returnCommandHandler;
        try {
            Class<?> commandClass = command.getClass();
            Class<?> commandHandlerClass = Class.forName(this.getClass().getName());
            Method commandHandlerMethodToBeCalled = getCommandHandlerMethod(commandClass, commandHandlerClass);
            commandHandlerMethodToBeCalled.setAccessible(true);
            returnCommandHandler = commandHandlerMethodToBeCalled.invoke(this, command);
            commandHandlerMethodToBeCalled.setAccessible(false);
        } catch (Exception excecao) {
            throw new CommandHandlerException(excecao);
        }
        return returnCommandHandler != null ? (ReturnCommandHandler) returnCommandHandler :
                ReturnCommandHandler.of();
    }

    private Method getCommandHandlerMethod(Class<?> commandClass, Class<?> commandHandlerClass) {
        return Stream.of(commandHandlerClass.getDeclaredMethods())
                        .filter(method -> method.getParameterCount() == 1 && method.getParameterTypes()[0] == commandClass)
                        .findAny()
                        .orElseThrow(() -> new CommandHandlerException(
                                MessageFormat.format("It could not find a method with parameter {0} in {1}.",
                                        commandHandlerClass.getSimpleName(), commandClass.getSimpleName())));
    }

    protected void applyToEntity(UniqueIdentifier id, Consumer consumer) {
        try {
            EventStream eventStream = eventStore.get(id);
            Class<E> entityClass = getGenericTypeClass();
            Constructor<E> constructor = entityClass.getDeclaredConstructor(List.class);
            E entity = constructor.newInstance(eventStream.events());
            consumer.accept(entity);
            eventStore.add(entity, eventStream.currentVersion(), entity.changes());
        } catch (Exception e) {
            throw new CommandHandlerException(e);
        }
    }

    private Class<E> getGenericTypeClass() {
        return (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
