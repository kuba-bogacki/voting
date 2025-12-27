package com.voting.controller;

import com.voting.service.ElectionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.voting.util.ApplicationConstants.API_VERSION;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = API_VERSION + "/elections")
public class ElectionsController {

    private final ElectionsService electionsService;
}
