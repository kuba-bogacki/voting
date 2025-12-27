package com.voting.mapper;

import com.voting.model.dto.VoterDto;
import com.voting.model.entity.Voter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VoterMapper {

    @Mapping(target = "voterId", ignore = true)
    @Mapping(target = "voterStatus", ignore = true)
    Voter mapToVoterEntity(VoterDto voterDto);

    VoterDto mapToVoterDto(Voter voter);
}
