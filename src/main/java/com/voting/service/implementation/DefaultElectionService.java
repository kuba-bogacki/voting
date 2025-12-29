package com.voting.service.implementation;

import com.voting.exception.ElectionException;
import com.voting.mapper.ElectionMapper;
import com.voting.model.dto.ElectionRequest;
import com.voting.model.dto.VotingRequest;
import com.voting.model.type.VoterStatus;
import com.voting.repository.ElectionRepository;
import com.voting.service.ElectionService;
import com.voting.service.OptionService;
import com.voting.service.VoterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultElectionService implements ElectionService {

    private final VoterService voterService;
    private final OptionService optionService;
    private final ElectionMapper electionMapper;
    private final ElectionRepository electionRepository;

    @Override
    @Transactional
    public void createNewElection(ElectionRequest electionRequest) {
        final var existingElection = Optional.ofNullable(electionRepository.findElectionByElectionName(electionRequest.getElectionName()));

        if (existingElection.isPresent()) {
            log.error("Cannot save new election entity because election with provided name already exist");
            throw new ElectionException(String.format("Election with name %s already exist", electionRequest.getElectionName()));
        }

        final var optionsEntities = electionRequest.getElectionOptions().stream()
                .map(optionService::saveNewOption)
                .collect(Collectors.toSet());

        final var electionEntity = electionMapper.mapToElectionEntity(electionRequest);
        optionsEntities.forEach(electionEntity::addOption);

        final var savedElection = electionRepository.save(electionEntity);
        log.info("New election entity has been saved with id {}", savedElection.getElectionId());
    }

    @Override
    @Transactional
    public void voteInElection(VotingRequest votingRequest) {
        final var voterEmail = votingRequest.getVoterEmail();
        final var electionName = votingRequest.getElectionName();
        final var optionName = votingRequest.getOptionName();

        final var existingVoter = voterService.getVoter(voterEmail);

        if (existingVoter.getVoterStatus() == VoterStatus.BLOCKED) {
            log.error("Current voter cannot vote in particular election because voter is blocked");
            throw new ElectionException(String.format("Voter with email %s is blocked", voterEmail));
        }

        final var existingElection = Optional.ofNullable(electionRepository.findElectionByElectionName(electionName));

        if (existingElection.isEmpty()) {
            log.error("Cannot add vote for current election because election with provided name not found");
            throw new ElectionException(String.format("Election with name %s not found", electionName));
        }

        if (existingElection.get().hasAlreadyVoted(existingVoter)) {
            log.error("Current voter cannot add vote in current election because voter has already voted");
            throw new ElectionException(String.format("Voter with email %s already voted", voterEmail));
        }

        if (existingElection.get().isOptionNameNotExist(optionName)) {
            log.error("Cannot add vote in current election because provided option not exist in particular election");
            throw new ElectionException(String.format("Option with name %s not exist", optionName));
        }

        optionService.addVoteForOption(existingElection.get().getOptionId(optionName));
        existingElection.get().addVoter(existingVoter);
        electionRepository.save(existingElection.get());
        log.info("Voter with email {} vote successfully in election with name {} on option {}", voterEmail, electionName, optionName);
    }
}
