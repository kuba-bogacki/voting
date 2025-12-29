package com.voting.service.implementation;

import com.voting.exception.ElectionException;
import com.voting.mapper.ElectionMapper;
import com.voting.model.dto.OptionRequest;
import com.voting.model.entity.Election;
import com.voting.model.entity.Option;
import com.voting.service.OptionService;
import com.voting.service.VoterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;

class DefaultElectionServiceTest extends VotingSamples {

    private final ElectionMapper electionMapper = Mappers.getMapper(ElectionMapper.class);
    private OptionService optionService;
    private VoterService voterService;
    private FakeElectionRepository electionRepository;
    private DefaultElectionService electionService;

    @BeforeEach
    void setUp() {
//        optionService = this::saveEntity;
        electionRepository = new FakeElectionRepository();
        electionService = new DefaultElectionService(voterService, optionService, electionMapper, electionRepository);
    }

    @Test
    @DisplayName("Should create new election entity if election with provided name not exist")
    void test_01() {
        //when
        electionService.createNewElection(electionRequestNo2);

        //then
        assertThat(electionRepository.findElectionByElectionName(electionRequestNo2.getElectionName()))
                .isEqualTo(electionRepository.getStubElection());
    }

    @Test
    @DisplayName("Should fail if try to save new election entity and election with provided name already exist")
    void test_02() {
        //when
        final var expectedException = catchException(() -> electionService.createNewElection(electionRequestNo1));

        //then
        assertThat(expectedException)
                .isInstanceOf(ElectionException.class)
                .hasMessageContaining(String.format("Election with name %s already exist", electionRequestNo1.getElectionName()));
    }

    @Test
    @DisplayName("Should fail if error occur due saving one of option entity")
    void test_03() {
        //given
//        optionService = optionRequest -> {
//            throw new IllegalArgumentException("Option entity cannot be null");
//        };
//        electionService = new DefaultElectionService(optionService, electionMapper, electionRepository);

        //when
        final var expectedException = catchException(() -> electionService.createNewElection(electionRequestNo2));

        //then
        assertThat(expectedException)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Option entity cannot be null");
    }

    @Test
    @DisplayName("Should fail if error occur due saving election entity")
    void test_04() {
        //given
        electionRepository = new FakeElectionRepository() {
            @Override
            public <S extends Election> S save(S entity) {
                throw new IllegalArgumentException("Entity cannot be null");
            }
        };
//        electionService = new DefaultElectionService(optionService, electionMapper, electionRepository);

        //when
        final var expectedException = catchException(() -> electionService.createNewElection(electionRequestNo2));

        //then
        assertThat(expectedException)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Entity cannot be null");
    }

    private Option saveEntity(OptionRequest optionRequest) {
        if (optionRequest.equals(optionRequestNo1)) {
            return optionEntityNo1;
        }
        if (optionRequest.equals(optionRequestNo2)) {
            return optionEntityNo2;
        }
        if (optionRequest.equals(optionRequestNo3)) {
            return optionEntityNo3;
        }
        if (optionRequest.equals(optionRequestNo4)) {
            return optionEntityNo4;
        }
        return null;
    }
}