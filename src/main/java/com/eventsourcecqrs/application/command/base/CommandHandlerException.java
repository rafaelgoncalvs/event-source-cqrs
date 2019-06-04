package com.eventsourcecqrs.application.command.base;

public class CommandHandlerException extends RuntimeException {
    public CommandHandlerException(Exception excecao) {
        super(excecao);
    }
}
