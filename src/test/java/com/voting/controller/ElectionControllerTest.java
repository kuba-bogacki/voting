package com.voting.controller;

import com.voting.exception.ElectionException;
import com.voting.exception.handler.GlobalExceptionHandler;
import com.voting.service.ElectionService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.voting.util.ApplicationConstants.API_VERSION;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ElectionController.class)
@Import(GlobalExceptionHandler.class)
class ElectionControllerTest extends VotingSamples {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ElectionService electionService;

    @Test
    @SneakyThrows
    @DisplayName("Should return status 201 if create new election successfully")
    void test_01() {
        //when
        mockMvc.perform(post(API_VERSION + electionRequestMapping + createPostMapping)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(electionRequest))
        //then
                .andExpect(status().isCreated());
    }

    @Test
    @SneakyThrows
    @DisplayName("Should return status 403 when try to create new election and election already exists")
    void test_02() {
        //when
        doThrow(new ElectionException("Election with provided name already exists"))
                .when(electionService)
                .createNewElection(any());

        mockMvc.perform(post(API_VERSION + electionRequestMapping + createPostMapping)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(electionRequest))
        //then
                .andExpect(status().isForbidden());
    }

    @Test
    @SneakyThrows
    @DisplayName("Should return status 202 if voting in election pass")
    void test_03() {
        //when
        mockMvc.perform(patch(API_VERSION + electionRequestMapping + votePatchMapping)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(votingRequest))
        //then
                .andExpect(status().isAccepted());
    }

    @Test
    @SneakyThrows
    @DisplayName("Should return status 403 when try to vote in election and election option not exists")
    void test_04() {
        //when
        doThrow(new ElectionException("Election option with provided name not exists"))
                .when(electionService)
                .voteInElection(any());

        mockMvc.perform(patch(API_VERSION + electionRequestMapping + votePatchMapping)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(votingRequest))
        //then
                .andExpect(status().isForbidden());
    }
}