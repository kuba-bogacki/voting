package com.voting.service;

import com.voting.model.dto.VoterRequest;
import com.voting.model.dto.VoterStatusRequest;
import com.voting.model.entity.Voter;

public interface VoterService {
    void createNewVoter(VoterRequest voterRequest);
    void changeVoterStatus(VoterStatusRequest voterStatusRequest);
    Voter getVoter(String voterEmail);
}
