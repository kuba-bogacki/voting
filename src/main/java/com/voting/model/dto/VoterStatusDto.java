package com.voting.model.dto;

import com.voting.model.type.VoterStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VoterStatusDto {

    @Email(message = "Voter email must be a valid email")
    @NotBlank(message = "Voter email cannot be blank")
    private String voterEmail;

    @NotBlank(message = "Voter status cannot be blank")
    private VoterStatus voterStatus;
}
