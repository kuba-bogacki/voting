package com.voting.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class ElectionRequest {

    @Size(min = 2, max = 64)
    @NotBlank(message = "Election name cannot be blank")
    private String electionName;

    @Size(min = 2, message = "Election options set must contain min 2 elements")
    @NotEmpty(message = "Election options cannot be empty")
    private Set<OptionRequest> electionOptions;
}
