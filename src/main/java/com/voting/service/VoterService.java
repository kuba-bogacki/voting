package com.voting.service;

import com.voting.model.dto.VoterRequest;
import com.voting.model.dto.VoterStatusRequest;

public interface VoterService {
    void createNewVoter(VoterRequest voterRequest);
    void changeVoterStatus(VoterStatusRequest voterStatusRequest);
}
