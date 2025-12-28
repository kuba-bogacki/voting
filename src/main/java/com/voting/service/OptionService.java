package com.voting.service;

import com.voting.model.dto.OptionRequest;
import com.voting.model.entity.Option;

import java.util.Set;
import java.util.UUID;

public interface OptionService {
    Option saveNewOption(OptionRequest optionRequest);
}
