package com.voting.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class VotingRequest {

    @Email(message = "Voter email must be a valid email")
    @NotBlank(message = "Voter email cannot be blank")
    private String voterEmail;

    @Size(min = 2, max = 64)
    @NotBlank(message = "Election name cannot be blank")
    private String electionName;

    @Size(min = 2, max = 64)
    @NotBlank(message = "Option name cannot be blank")
    private String optionName;
}
