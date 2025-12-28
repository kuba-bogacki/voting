package com.voting.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class OptionRequest {

    @Size(min = 2, max = 64)
    @NotBlank(message = "Option name cannot be blank")
    private String optionName;
}
