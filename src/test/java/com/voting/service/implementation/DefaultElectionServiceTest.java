package com.voting.service.implementation;

import com.voting.exception.ElectionException;
import com.voting.mapper.ElectionMapper;
import com.voting.model.dto.OptionRequest;
import com.voting.model.dto.VoterRequest;
import com.voting.model.dto.VoterStatusRequest;
import com.voting.model.entity.Election;
import com.voting.model.entity.Option;
import com.voting.model.entity.Voter;
import com.voting.model.type.VoterStatus;
import com.voting.service.OptionService;
import com.voting.service.VoterService;
import lombok.Getter;
import org.apache.commons.lang3.NotImplementedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;
import static org.junit.jupiter.api.Assertions.assertAll;

class DefaultElectionServiceTest extends VotingSamples {

    private final ElectionMapper electionMapper = Mappers.getMapper(ElectionMapper.class);
    private DummyVoterService voterService;
    private DummyOptionService optionService;
    private FakeElectionRepository electionRepository;
    private DefaultElectionService electionService;

    @BeforeEach
    void setUp() {
        voterService = new DummyVoterService();
        optionService = new DummyOptionService();
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
        optionService = new DummyOptionService() {
            @Override
            public Option saveNewOption(OptionRequest optionRequest) {
                throw new IllegalArgumentException("Option entity cannot be null");
            }
        };
        electionService = new DefaultElectionService(voterService, optionService, electionMapper, electionRepository);

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
        electionService = new DefaultElectionService(voterService, optionService, electionMapper, electionRepository);

        //when
        final var expectedException = catchException(() -> electionService.createNewElection(electionRequestNo2));

        //then
        assertThat(expectedException)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Entity cannot be null");
    }

    @Test
    @DisplayName("Should vote in election on chosen option if election with provided name exist and voter is not blocked")
    void test_05() {
        //when
        electionService.voteInElection(votingRequestNo1);

        //then
        assertAll(
                () -> assertThat(electionRepository.getStubElection().getElectionVoters().size()).isEqualTo(1),
                () -> assertThat(optionService.isHasVoted()).isTrue()
        );
    }

    @Test
    @DisplayName("Should fail and block voting in election if voter status is blocked")
    void test_06() {
        //given
        final var blockedVoterVotingRequest = votingRequestNo1.toBuilder()
                .voterEmail(voterEmailNo2)
                .build();

        voterService = new DummyVoterService() {
            @Override
            public Voter getVoter(String voterEmail) {
                return voterEntityNo2;
            }
        };
        electionService = new DefaultElectionService(voterService, optionService, electionMapper, electionRepository);

        //when
        final var expectedException = catchException(() -> electionService.voteInElection(blockedVoterVotingRequest));

        //then
        assertThat(expectedException)
                .isInstanceOf(ElectionException.class)
                .hasMessageContaining(String.format("Voter with email %s is blocked", voterEmailNo2));
    }

    @Test
    @DisplayName("Should fail and block voting in election if election with provided name not exist")
    void test_07() {
        //given
        final var notExistElectionVotingRequest = votingRequestNo1.toBuilder()
                .electionName(electionNameNo2)
                .build();

        //when
        final var expectedException = catchException(() -> electionService.voteInElection(notExistElectionVotingRequest));

        //then
        assertThat(expectedException)
                .isInstanceOf(ElectionException.class)
                .hasMessageContaining(String.format("Election with name %s not found", electionNameNo2));
    }

    @Test
    @DisplayName("Should fail and block voting in election if voter previously vote in election")
    void test_08() {
        //given
        final var previouslyVotedVotingRequest = votingRequestNo1.toBuilder()
                .voterEmail(voterEmailNo2)
                .electionName(electionNameNo3)
                .build();

        voterService = new DummyVoterService() {
            @Override
            public Voter getVoter(String voterEmail) {
                return voterEntityNo2.toBuilder()
                        .voterStatus(VoterStatus.UNBLOCKED)
                        .build();
            }
        };
        electionService = new DefaultElectionService(voterService, optionService, electionMapper, electionRepository);

        //when
        final var expectedException = catchException(() -> electionService.voteInElection(previouslyVotedVotingRequest));

        //then
        assertThat(expectedException)
                .isInstanceOf(ElectionException.class)
                .hasMessageContaining(String.format("Voter with email %s already voted", voterEmailNo2));
    }

    @Test
    @DisplayName("Should fail and block voting in election if option name not exist")
    void test_09() {
        //given
        final var notExistOptionVotingRequest = votingRequestNo1.toBuilder()
                .optionName(optionNameNo1)
                .electionName(electionNameNo3)
                .build();

        //when
        final var expectedException = catchException(() -> electionService.voteInElection(notExistOptionVotingRequest));

        //then
        assertThat(expectedException)
                .isInstanceOf(ElectionException.class)
                .hasMessageContaining(String.format("Option with name %s not exist", optionNameNo1));
    }

    @Test
    @DisplayName("Should throw an exception when error occur due saving updated election")
    void test_10() {
        //given
        electionRepository = new FakeElectionRepository() {
            @Override
            public <S extends Election> S save(S entity) {
                throw new IllegalArgumentException("Entity cannot be null");
            }
        };
        electionService = new DefaultElectionService(voterService, optionService, electionMapper, electionRepository);

        //when
        final var expectedException = catchException(() -> electionService.voteInElection(votingRequestNo1));

        //then
        assertThat(expectedException)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Entity cannot be null");
    }

    static class DummyVoterService extends VotingSamples implements VoterService {

        @Override
        public void createNewVoter(VoterRequest voterRequest) {
            // No implementation for test purposes needed
            throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
        }

        @Override
        public void changeVoterStatus(VoterStatusRequest voterStatusRequest) {
            // No implementation for test purposes needed
            throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
        }

        @Override
        public Voter getVoter(String voterEmail) {
            return voterEntityNo1;
        }
    }

    @Getter
    static class DummyOptionService extends VotingSamples implements OptionService {

        private boolean hasVoted = false;

        @Override
        public Option saveNewOption(OptionRequest optionRequest) {
            return optionEntityNo1;
        }

        @Override
        public void addVoteForOption(UUID optionId) {
            this.hasVoted = true;
        }
    }
}