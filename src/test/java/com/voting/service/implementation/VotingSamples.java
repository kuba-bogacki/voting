package com.voting.service.implementation;

import com.voting.model.dto.*;
import com.voting.model.entity.Election;
import com.voting.model.entity.Option;
import com.voting.model.entity.Voter;
import com.voting.model.type.VoterStatus;

import java.util.Set;
import java.util.UUID;

class VotingSamples {

    final static String NO_IMPLEMENTED_METHOD = "No implementation for test purposes needed";

    final UUID voterIdNo1 = UUID.fromString("60a517c0-d6be-4f29-9af9-16a6fa245085");
    final UUID voterIdNo2 = UUID.fromString("b2456b3b-7ecd-4e96-94c7-49f103d7b1ed");
    final UUID optionIdNo1 = UUID.fromString("4572adb5-8834-4df4-b7f7-a44c00303c49");
    final UUID optionIdNo2 = UUID.fromString("9f56cc17-de70-486b-855f-c7d3a9b9a726");
    final UUID electionIdNo1 = UUID.fromString("6a68e231-53b6-4a45-bb08-72dd8ec86fb0");
    final UUID electionIdNo3 = UUID.fromString("4b3014a8-51bc-47e0-9840-4f60c5e5873f");
    final String voterEmailNo1 = "adrien@gmail.com";
    final String voterEmailNo2 = "nicolas@gmail.com";
    final String voterFirstNameNo1 = "Adrien";
    final String voterFirstNameNo2 = "Nicolas";
    final String voterLastNameNo1 = "Brody";
    final String voterLastNameNo2 = "Cage";
    final String optionNameNo1 = "Joe Biden";
    final String optionNameNo2 = "Donald Trump";
    final String optionNameNo3 = "Kamala Harris";
    final String optionNameNo4 = "Barrack Obama";
    final String electionNameNo1 = "USA President Election 2025";
    final String electionNameNo2 = "USA President Election 2030";
    final String electionNameNo3 = "USA President Election 2035";

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
            .voterId(voterIdNo1)
            .voterEmail(voterEmailNo1)
            .voterFirstName(voterFirstNameNo1)
            .voterLastName(voterLastNameNo1)
            .build();

    Voter voterEntityNo2 = Voter.builder()
            .voterId(voterIdNo2)
            .voterEmail(voterEmailNo2)
            .voterFirstName(voterFirstNameNo2)
            .voterLastName(voterLastNameNo2)
            .voterStatus(VoterStatus.BLOCKED)
            .build();

    OptionRequest optionRequestNo1 = OptionRequest.builder()
            .optionName(optionNameNo1)
            .build();

    OptionRequest optionRequestNo2 = OptionRequest.builder()
            .optionName(optionNameNo2)
            .build();

    OptionRequest optionRequestNo3 = OptionRequest.builder()
            .optionName(optionNameNo3)
            .build();

    OptionRequest optionRequestNo4 = OptionRequest.builder()
            .optionName(optionNameNo4)
            .build();

    Option optionEntityNo1 = Option.builder()
            .optionId(optionIdNo1)
            .optionName(optionNameNo1)
            .build();

    Option optionEntityNo2 = Option.builder()
            .optionId(optionIdNo2)
            .optionName(optionNameNo2)
            .build();

    Option optionEntityNo3 = Option.builder()
            .optionName(optionNameNo3)
            .build();

    Option optionEntityNo4 = Option.builder()
            .optionName(optionNameNo4)
            .build();

    ElectionRequest electionRequestNo1 = ElectionRequest.builder()
            .electionName(electionNameNo1)
            .electionOptions(Set.of(optionRequestNo1, optionRequestNo2))
            .build();

    ElectionRequest electionRequestNo2 = ElectionRequest.builder()
            .electionName(electionNameNo2)
            .electionOptions(Set.of(optionRequestNo3, optionRequestNo4))
            .build();

    Election electionEntityNo1 = Election.builder()
            .electionId(electionIdNo1)
            .electionName(electionNameNo1)
            .electionOptions(Set.of(optionEntityNo1, optionEntityNo2))
            .build();

    Election electionEntityNo3 = Election.builder()
            .electionId(electionIdNo3)
            .electionName(electionNameNo3)
            .electionOptions(Set.of(optionEntityNo3, optionEntityNo4))
            .electionVoters(Set.of(voterEntityNo2))
            .build();

    VotingRequest votingRequestNo1 = VotingRequest.builder()
            .voterEmail(voterEmailNo1)
            .electionName(electionNameNo1)
            .optionName(optionNameNo1)
            .build();
}
