package com.voting.service.implementation;

import com.voting.model.dto.VoterRequest;
import com.voting.model.dto.VoterStatusRequest;
import com.voting.model.entity.Voter;
import com.voting.model.type.VoterStatus;

import java.util.UUID;

class VotingSamples {

    final UUID voterIdNo1 = UUID.fromString("60a517c0-d6be-4f29-9af9-16a6fa245085");
    final UUID voterIdNo2 = UUID.fromString("b2456b3b-7ecd-4e96-94c7-49f103d7b1ed");
    final String voterEmailNo1 = "adrien@gmail.com";
    final String voterEmailNo2 = "nicolas@gmail.com";
    final String voterFirstNameNo1 = "Adrien";
    final String voterFirstNameNo2 = "Nicolas";
    final String voterLastNameNo1 = "Brody";
    final String voterLastNameNo2 = "Cage";

    VoterRequest voterRequestNo1 = VoterRequest.builder()
            .voterEmail(voterEmailNo1)
            .voterFirstName(voterFirstNameNo1)
            .voterLastName(voterLastNameNo1)
            .build();

    VoterRequest voterRequestNo2 = VoterRequest.builder()
            .voterEmail(voterEmailNo2)
            .voterFirstName(voterFirstNameNo2)
            .voterLastName(voterLastNameNo2)
            .build();

    VoterStatusRequest voterStatusRequestNo1 = VoterStatusRequest.builder()
            .voterEmail(voterEmailNo1)
            .voterStatus(VoterStatus.BLOCKED)
            .build();

    VoterStatusRequest voterStatusRequestNo2 = VoterStatusRequest.builder()
            .voterEmail(voterEmailNo2)
            .voterStatus(VoterStatus.BLOCKED)
            .build();

    Voter voterEntityNo1 = Voter.builder()
            .voterEmail(voterEmailNo1)
            .voterFirstName(voterFirstNameNo1)
            .voterLastName(voterLastNameNo1)
            .build();

    Voter voterEntityNo2 = Voter.builder()
            .voterEmail(voterEmailNo2)
            .voterFirstName(voterFirstNameNo2)
            .voterLastName(voterLastNameNo2)
            .build();

    Voter savedVoterNo1 = Voter.builder()
            .voterId(voterIdNo1)
            .voterEmail(voterEmailNo1)
            .voterFirstName(voterFirstNameNo1)
            .voterLastName(voterLastNameNo1)
            .build();

    Voter savedVoterNo2 = Voter.builder()
            .voterId(voterIdNo2)
            .voterEmail(voterEmailNo2)
            .voterFirstName(voterFirstNameNo2)
            .voterLastName(voterLastNameNo2)
            .build();
}
