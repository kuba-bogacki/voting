package com.voting.service.implementation;

import com.voting.model.dto.VoterDto;
import com.voting.model.entity.Voter;

import java.util.UUID;

class VotingSamples {

    final UUID voterId = UUID.fromString("60a517c0-d6be-4f29-9af9-16a6fa245085");
    final String voterEmail = "voter@gmail.com";
    final String voterFirstName = "Adrien";
    final String voterLastName = "Brody";

    VoterDto voterDto = VoterDto.builder()
            .voterEmail(voterEmail)
            .voterFirstName(voterFirstName)
            .voterLastName(voterLastName)
            .build();

    Voter voterEntity = Voter.builder()
            .voterEmail(voterEmail)
            .voterFirstName(voterFirstName)
            .voterLastName(voterLastName)
            .build();

    Voter savedVoter = Voter.builder()
            .voterId(voterId)
            .voterEmail(voterEmail)
            .voterFirstName(voterFirstName)
            .voterLastName(voterLastName)
            .build();
}
