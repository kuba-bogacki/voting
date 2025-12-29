package com.voting.service.implementation;

import com.voting.exception.VoterException;
import com.voting.mapper.VoterMapper;
import com.voting.model.dto.VoterRequest;
import com.voting.model.dto.VoterStatusRequest;
import com.voting.model.entity.Voter;
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
        final var existingVoter = voterRepository.findVoterByVoterEmail(voterStatusRequest.getVoterEmail());

        confirmVoterExistence(existingVoter, voterStatusRequest.getVoterEmail());

        existingVoter.setVoterStatus(voterStatusRequest.getVoterStatus());
        final var savedVoter = voterRepository.save(existingVoter);
        log.info("Voter status has been updated to {} status", savedVoter.getVoterStatus());
    }

    @Override
    public Voter getVoter(String voterEmail) {
        final var existingVoter = voterRepository.findVoterByVoterEmail(voterEmail);

        confirmVoterExistence(existingVoter, voterEmail);

        log.info("Voter entity with email {} has been found", voterEmail);
        return existingVoter;
    }

    private void confirmVoterExistence(Voter voter, String voterEmail) {
        if (Optional.ofNullable(voter).isEmpty()) {
            log.error("Cannot find voter with provided email address {}", voterEmail);
            throw new VoterException(String.format("Voter with email %s not exist", voterEmail));
        }
    }
}
