package com.voting.service.implementation;

import com.voting.exception.VoterException;
import com.voting.mapper.VoterMapper;
import com.voting.model.dto.VoterRequest;
import com.voting.model.dto.VoterStatusRequest;
import com.voting.repository.VoterRepository;
import com.voting.service.VoterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultVoterService implements VoterService {

    private final VoterMapper voterMapper;
    private final VoterRepository voterRepository;

    @Override
    public void createNewVoter(VoterRequest voterRequest) {
        final var existingVoter = Optional.ofNullable(voterRepository.findVoterByVoterEmail(voterRequest.getVoterEmail()));

        if (existingVoter.isPresent()) {
            log.error("Cannot save new voter entity because voter with provided email address already exist");
            throw new VoterException(String.format("Voter with email %s already exist", voterRequest.getVoterEmail()));
        }

        final var voterEntity = voterMapper.mapToVoterEntity(voterRequest);
        final var savedVoter = voterRepository.save(voterEntity);
        log.info("New voter entity has been saved with id {}", savedVoter.getVoterId());
    }

    @Override
    public void changeVoterStatus(VoterStatusRequest voterStatusRequest) {
        final var existingVoter = Optional.ofNullable(voterRepository.findVoterByVoterEmail(voterStatusRequest.getVoterEmail()));

        if (existingVoter.isEmpty()) {
            log.error("Cannot find voter with provided email address {}", voterStatusRequest.getVoterEmail());
            throw new VoterException(String.format("Voter with email %s not exist", voterStatusRequest.getVoterEmail()));
        }

        existingVoter.get().setVoterStatus(voterStatusRequest.getVoterStatus());
        final var savedVoter = voterRepository.save(existingVoter.get());
        log.info("Voter status has been updated to {} status", savedVoter.getVoterStatus());
    }
}
