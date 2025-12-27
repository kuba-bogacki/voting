package com.voting.controller;

import com.voting.model.dto.VoterDto;
import com.voting.model.dto.VoterStatusDto;
import com.voting.service.VoterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.voting.util.ApplicationConstants.API_VERSION;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = API_VERSION + "/voter")
public class VoterController {

    private final VoterService voterService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createNewVoter(@RequestBody @Valid VoterDto voterDto) {
        voterService.createNewVoter(voterDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping(value = "/update")
    public ResponseEntity<?> updateVoterStatus(@RequestBody @Valid VoterStatusDto voterStatusDto) {
        voterService.changeVoterStatus(voterStatusDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
