package com.voting.mapper;

import com.voting.model.dto.ElectionRequest;
import com.voting.model.entity.Election;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ElectionMapper {

    @Mapping(target = "electionId", ignore = true)
    @Mapping(target = "electionOptions", ignore = true)
    @Mapping(target = "electionVoters", ignore = true)
    Election mapToElectionEntity(ElectionRequest electionRequest);
}
