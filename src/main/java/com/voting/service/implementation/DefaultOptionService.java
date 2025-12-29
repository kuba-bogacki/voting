package com.voting.service.implementation;

import com.voting.exception.OptionException;
import com.voting.exception.VoterException;
import com.voting.mapper.OptionMapper;
import com.voting.model.dto.OptionRequest;
import com.voting.model.entity.Option;
import com.voting.repository.OptionRepository;
import com.voting.service.OptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultOptionService implements OptionService {

    private final OptionMapper optionMapper;
    private final OptionRepository optionRepository;

    @Override
    public Option saveNewOption(OptionRequest optionRequest) {
        final var optionEntity = optionMapper.mapToOptionEntity(optionRequest);
        final var savedOption = optionRepository.save(optionEntity);
        log.info("New option entity has been saved with id {}", savedOption.getOptionId());
        return savedOption;
    }

    @Override
    public void addVoteForOption(UUID optionId) {
        final var existingOption = optionRepository.findById(optionId);

        if (existingOption.isEmpty()) {
            log.error("Cannot add vote for option because option entity with provided id not exist");
            throw new OptionException(String.format("Option with id %s not exist", optionId));
        }

        final long updatedVotesCount = existingOption.get().getOptionVotes() + 1;
        existingOption.get().setOptionVotes(updatedVotesCount);
        optionRepository.save(existingOption.get());
        log.info("Option vote count has been updated, actual vote count {}", updatedVotesCount);
    }
}
