package com.voting.service.implementation;

import com.voting.exception.VoterException;
import com.voting.mapper.VoterMapper;
import com.voting.model.type.VoterStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;

class DefaultVoterServiceTest extends VotingSamples {

    private final VoterMapper voterMapper = Mappers.getMapper(VoterMapper.class);
    private FakeVoterRepository voterRepository;
    private DefaultVoterService voterService;

    @BeforeEach
    void setUp() {
        voterRepository = new FakeVoterRepository();
        voterService = new DefaultVoterService(voterMapper, voterRepository);
    }

    @Test
    @DisplayName("Should create new voter entity if voter with provided email not exist")
    void test_01() {
        //when
        voterService.createNewVoter(voterRequestNo2);

        //then
        assertThat(voterRepository.findVoterByVoterEmail(voterRequestNo2.getVoterEmail()))
                .isEqualTo(voterRepository.getStubVoter());
    }

    @Test
    @DisplayName("Should fail if try to save new voter entity and voter with provided email already exist")
    void test_02() {
        //when
        final var expectedException = catchException(() -> voterService.createNewVoter(voterRequestNo1));

        //then
        assertThat(expectedException)
                .isInstanceOf(VoterException.class)
                .hasMessageContaining(String.format("Voter with email %s already exist", voterEmailNo1));
    }

    @Test
    @DisplayName("Should update voter status to blocked if voter with provided email already exist")
    void test_03() {
        //when
        voterService.changeVoterStatus(voterStatusRequestNo1);

        //then
        assertThat(voterRepository.findVoterByVoterEmail(voterStatusRequestNo1.getVoterEmail()).getVoterStatus())
                .isEqualTo(VoterStatus.BLOCKED);
    }

    @Test
    @DisplayName("Should update voter status to unblocked if voter with provided email already exist")
    void test_04() {
        //given
        final var unblockedVoterStatusDto = voterStatusRequestNo1.toBuilder()
                .voterStatus(VoterStatus.UNBLOCKED)
                .build();

        //when
        voterService.changeVoterStatus(unblockedVoterStatusDto);

        //then
        assertThat(voterRepository.findVoterByVoterEmail(voterStatusRequestNo1.getVoterEmail()).getVoterStatus())
                .isEqualTo(VoterStatus.UNBLOCKED);
    }

    @Test
    @DisplayName("Should fail if voter to update with provided email not exist")
    void test_05() {
        //when
        final var expectedException = catchException(() -> voterService.changeVoterStatus(voterStatusRequestNo2));

        //then
        assertThat(expectedException)
                .isInstanceOf(VoterException.class)
                .hasMessageContaining(String.format("Voter with email %s not exist", voterEmailNo2));
    }
}