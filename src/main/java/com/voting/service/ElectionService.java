package com.voting.service;

import com.voting.model.dto.ElectionRequest;
import com.voting.model.dto.VotingRequest;

public interface ElectionService {
    void createNewElection(ElectionRequest electionRequest);
    void voteInElection(VotingRequest votingRequest);
}
