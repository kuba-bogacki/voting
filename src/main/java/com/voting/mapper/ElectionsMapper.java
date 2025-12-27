package com.voting.mapper;

import com.voting.model.entity.Elections;
import com.voting.model.dto.ElectionsDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ElectionsMapper {

    Elections mapToElectionsEntity(ElectionsDto electionsDto);
    ElectionsDto mapToElectionsDto(Elections elections);
}
