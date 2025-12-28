package com.voting.service.implementation;

import com.voting.exception.OptionException;
import com.voting.mapper.OptionMapper;
import com.voting.model.dto.OptionRequest;
import com.voting.model.entity.Option;
import com.voting.repository.OptionRepository;
import com.voting.service.OptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
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
}
