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
public class VoterDto {

    @Email(message = "Voter email must be a valid email")
    @NotBlank(message = "Voter email cannot be blank")
    private String voterEmail;

    @Size(min = 2, max = 64)
    @NotBlank(message = "Voter first name cannot be blank")
    private String voterFirstName;

    @Size(min = 2, max = 64)
    @NotBlank(message = "Voter last name cannot be blank")
    private String voterLastName;
}
