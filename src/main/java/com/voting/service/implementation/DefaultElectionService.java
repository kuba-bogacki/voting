package com.voting.service.implementation;

import com.voting.exception.ElectionException;
import com.voting.mapper.ElectionMapper;
import com.voting.model.dto.ElectionRequest;
import com.voting.repository.ElectionRepository;
import com.voting.service.ElectionService;
import com.voting.service.OptionService;
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
}
