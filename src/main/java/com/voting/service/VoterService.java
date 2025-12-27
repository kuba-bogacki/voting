package com.voting.service;

import com.voting.model.dto.VoterDto;
import com.voting.model.dto.VoterStatusDto;

public interface VoterService {
    void createNewVoter(VoterDto voterDto);
    void changeVoterStatus(VoterStatusDto voterStatusDto);
}
