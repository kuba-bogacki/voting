package com.voting.service;

import com.voting.model.dto.ElectionRequest;

public interface ElectionService {
    void createNewElection(ElectionRequest electionRequest);
}
