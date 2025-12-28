package com.voting.mapper;

import com.voting.model.dto.OptionRequest;
import com.voting.model.entity.Option;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OptionMapper {

    @Mapping(target = "optionId", ignore = true)
    @Mapping(target = "optionVotes", ignore = true)
    @Mapping(target = "election", ignore = true)
    Option mapToOptionEntity(OptionRequest optionRequest);
}
