package com.eventsourcecqrs.application.command.writer;

import com.eventsourcecqrs.application.command.base.Command;

public class CreateWriterCommand extends Command {

    private String name;

    public CreateWriterCommand(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }
}
