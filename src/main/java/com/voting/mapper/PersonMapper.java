package com.voting.mapper;

import com.voting.model.entity.Person;
import com.voting.model.dto.PersonDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    Person mapToPersonEntity(PersonDto personDto);
    PersonDto mapToPersonDto(Person person);
}
