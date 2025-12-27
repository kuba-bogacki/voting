package com.voting.exception;

public class ElectionsException extends RuntimeException {

    public ElectionsException(String message) {
        super(String.format("Error occurred due operation related to handling elections: %s", message));
    }
}
