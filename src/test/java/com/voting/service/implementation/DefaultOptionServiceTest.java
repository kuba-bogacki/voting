package com.voting.service.implementation;

import com.voting.exception.OptionException;
import com.voting.mapper.OptionMapper;
import com.voting.model.dto.OptionRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;

class DefaultOptionServiceTest extends VotingSamples {

    private final OptionMapper optionMapper = Mappers.getMapper(OptionMapper.class);
    private FakeOptionRepository optionRepository;
    private DefaultOptionService optionService;

    @BeforeEach
    void setUp() {
        optionRepository = new FakeOptionRepository();
        optionService = new DefaultOptionService(optionMapper, optionRepository);
    }

    @Test
    @DisplayName("Should return new option entity after successful save")
    void test_01() {
        //when
        final var result = optionService.saveNewOption(optionRequestNo2);

        //then
        assertThat(result)
                .isEqualTo(optionRepository.getStubOption());
    }

    @Test
    @DisplayName("Should fail if option request is null")
    void test_02() {
        //given
        final OptionRequest nullOptionRequest = null;

        //when
        final var expectedException = catchException(() -> optionService.saveNewOption(nullOptionRequest));

        //then
        assertThat(expectedException)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Entity cannot be null");
    }

    @Test
    @DisplayName("Should save option entity after successfully updated vote")
    void test_03() {
        //when
        optionService.addVoteForOption(optionIdNo1);

        //then
        assertThat(optionRepository.getStubOption().getOptionVotes())
                .isEqualTo(1);
    }

    @Test
    @DisplayName("Should fail if option with provided id not exist")
    void test_04() {
        //when
        final var expectedException = catchException(() -> optionService.addVoteForOption(optionIdNo2));

        //then
        assertThat(expectedException)
                .isInstanceOf(OptionException.class)
                .hasMessageContaining(String.format("Option with id %s not exist", optionIdNo2));
    }
}