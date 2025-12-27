package com.voting.service.implementation;

import com.voting.mapper.VoterMapper;
import com.voting.model.dto.VoterDto;
import com.voting.model.entity.Voter;
import com.voting.repository.VoterRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DefaultVoterServiceTest extends VotingSamples {

    @Mock private VoterRepository voterRepository;
    @Mock private VoterMapper voterMapper;
    @Captor private ArgumentCaptor<Voter> voterCaptor;
    @InjectMocks private DefaultVoterService voterService;

    @Test
    @DisplayName("Should create new voter entity if voter with provided email not exist")
    void test_01() {
        //when
        when(voterRepository.findVoterByVoterEmail(voterEmail)).thenReturn(null);
        when(voterMapper.mapToVoterEntity(voterDto)).thenReturn(voterEntity);
        when(voterRepository.save(voterEntity)).thenReturn(savedVoter);

        voterService.createNewVoter(voterDto);

        //then
        verify(voterRepository, times(1)).save(voterCaptor.capture());
        assertAll(
                () -> assertThat(voterCaptor.getValue().getVoterEmail()).isEqualTo(voterEmail),
                () -> assertThat(voterCaptor.getValue().getVoterFirstName()).isEqualTo(voterFirstName),
                () -> assertThat(voterCaptor.getValue().getVoterLastName()).isEqualTo(voterLastName)
        );
    }
}