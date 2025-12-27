package com.voting.exception;

public class PersonException extends RuntimeException {

    public PersonException(String message) {
        super(String.format("Error occurred due operation related to handling person: %s", message));
    }
}
