package com.voting.controller;

import com.voting.model.dto.ElectionRequest;
import com.voting.service.ElectionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.voting.util.ApplicationConstants.API_VERSION;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = API_VERSION + "/election")
public class ElectionController {

    private final ElectionService electionService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createNewElection(@RequestBody @Valid ElectionRequest electionRequest) {
        electionService.createNewElection(electionRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
