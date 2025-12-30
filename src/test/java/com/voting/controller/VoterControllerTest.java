package com.voting.controller;

import com.voting.exception.VoterException;
import com.voting.service.VoterService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.voting.util.ApplicationConstants.API_VERSION;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VoterController.class)
class VoterControllerTest extends VotingSamples {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private VoterService voterService;

    @Test
    @SneakyThrows
    @DisplayName("Should return status 201 if create new voter successfully")
    void test_01() {
        //when
        mockMvc.perform(post(API_VERSION + voterRequestMapping + createPostMapping)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(voterRequest))
        //then
                .andExpect(status().isCreated());
    }

    @Test
    @SneakyThrows
    @DisplayName("Should return status 403 when try to create new voter and voter already exists")
    void test_02() {
        //when
        doThrow(new VoterException("Voter with provided email already exist"))
                .when(voterService)
                .createNewVoter(any());

        mockMvc.perform(post(API_VERSION + voterRequestMapping + createPostMapping)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(voterRequest))
        //then
                .andExpect(status().isForbidden());
    }

    @Test
    @SneakyThrows
    @DisplayName("Should return status 202 if updating voter status pass")
    void test_03() {
        //when
        mockMvc.perform(patch(API_VERSION + voterRequestMapping + updatePatchMapping)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(voterStatusRequest))
        //then
                .andExpect(status().isAccepted());
    }

    @Test
    @SneakyThrows
    @DisplayName("Should return status 403 when try to updating voter status but voter not exist")
    void test_04() {
        //when
        doThrow(new VoterException("Voter with provided email not exist"))
                .when(voterService)
                .changeVoterStatus(any());

        mockMvc.perform(patch(API_VERSION + voterRequestMapping + updatePatchMapping)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(voterStatusRequest))
        //then
                .andExpect(status().isForbidden());
    }
}
