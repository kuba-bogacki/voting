package com.voting.mapper;

import com.voting.model.entity.Election;
import com.voting.model.dto.ElectionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ElectionMapper {

    Election mapToElectionEntity(ElectionDto electionDto);
    ElectionDto mapToElectionDto(Election election);
}
