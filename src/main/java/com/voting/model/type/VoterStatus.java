package com.voting.model.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum VoterStatus {

    BLOCKED("BLOCKED"),
    UNBLOCKED("UNBLOCKED");

    private final String description;

    @JsonCreator
    public static VoterStatus fromString(String description) {
        for (VoterStatus currency : VoterStatus.values()) {
            if (currency.description.equalsIgnoreCase(description)) {
                return currency;
            }
        }
        throw new IllegalArgumentException(String.format("Impossible to create voter status type from: %s", description));
    }
}
