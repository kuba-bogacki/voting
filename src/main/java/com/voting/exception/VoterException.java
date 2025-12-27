package com.voting.exception;

public class VoterException extends RuntimeException {

    public VoterException(String message) {
        super(String.format("Error occurred due operation related to handling voter: %s", message));
    }
}
