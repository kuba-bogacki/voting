package com.voting.service.implementation;

import com.voting.exception.VoterException;
import com.voting.mapper.VoterMapper;
import com.voting.model.dto.VoterDto;
import com.voting.model.dto.VoterStatusDto;
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

    private final VoterRepository voterRepository;
    private final VoterMapper voterMapper;

    @Override
    public void createNewVoter(VoterDto voterDto) {
        final var existingVoter = Optional.ofNullable(voterRepository.findVoterByVoterEmail(voterDto.getVoterEmail()));

        if (existingVoter.isPresent()) {
            log.error("Cannot save new entity because voter with provided email address already exist");
            throw new VoterException(String.format("Voter with email %s already exist", voterDto.getVoterEmail()));
        }

        final var voterEntity = voterMapper.mapToVoterEntity(voterDto);
        final var savedVoter = voterRepository.save(voterEntity);
        log.info("New voter entity has been saved with id {}", savedVoter.getVoterId());
    }

    @Override
    public void changeVoterStatus(VoterStatusDto voterStatusDto) {
        final var existingVoter = Optional.ofNullable(voterRepository.findVoterByVoterEmail(voterStatusDto.getVoterEmail()));

        if (existingVoter.isEmpty()) {
            log.error("Cannot find voter with provided email address {}", voterStatusDto.getVoterEmail());
            throw new VoterException(String.format("Voter with email %s not exist", voterStatusDto.getVoterEmail()));
        }

        final var updatedVoter = existingVoter.get().toBuilder()
                .voterStatus(voterStatusDto.getVoterStatus())
                .build();
        final var savedVoter = voterRepository.save(updatedVoter);
        log.info("Voter status has been updated to {} status", savedVoter.getVoterStatus());
    }
}
