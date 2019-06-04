package com.eventsourcecqrs.application.command.book;

import com.eventsourcecqrs.application.command.base.Command;
import com.eventsourcecqrs.domain.model.writer.WriterId;

public class CreateBookCommand extends Command {

    private WriterId writerId;
    private String bookTitle;

    public CreateBookCommand(WriterId writerId, String bookTitle) {
        this.writerId = writerId;
        this.bookTitle = bookTitle;
    }

    public WriterId writerId() {
        return writerId;
    }

    public String bookTitle() {
        return bookTitle;
    }
}
