package com.voting.exception;

public class OptionException extends RuntimeException {

    public OptionException(String message) {
        super(String.format("Error occurred due operation related to handling option: %s", message));
    }
}
