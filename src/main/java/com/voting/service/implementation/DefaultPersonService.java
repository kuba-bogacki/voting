package com.voting.service.implementation;

import com.voting.repository.PersonRepository;
import com.voting.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultPersonService implements PersonService {

    private final PersonRepository personRepository;
}
