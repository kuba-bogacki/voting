package com.voting.exception;

public class ElectionException extends RuntimeException {

    public ElectionException(String message) {
        super(String.format("Error occurred due operation related to handling election: %s", message));
    }
}
