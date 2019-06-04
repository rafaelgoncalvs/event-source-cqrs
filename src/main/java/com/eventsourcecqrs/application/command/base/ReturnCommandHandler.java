package com.eventsourcecqrs.application.command.base;

import com.eventsourcecqrs.domain.model.base.UniqueIdentifier;

public class ReturnCommandHandler {

    private UniqueIdentifier returnedValue;

    private ReturnCommandHandler(UniqueIdentifier returnedValue) {
        this.returnedValue = returnedValue;
    }

    public static ReturnCommandHandler of() {
        return new ReturnCommandHandler(null);
    }

    public static ReturnCommandHandler of(UniqueIdentifier uniqueIdentifier) {
        return new ReturnCommandHandler(uniqueIdentifier);
    }

    public UniqueIdentifier value() {
        return returnedValue;
    }
}
