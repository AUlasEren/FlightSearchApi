package com.ulas.mapper;

import com.ulas.dto.request.CreateFlightsRequestDto;
import com.ulas.dto.request.CreateUserRequestDto;
import com.ulas.dto.request.UpdateUserRequestDto;
import com.ulas.repository.entity.Flights;
import com.ulas.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {
    IUserMapper INSTANCE= Mappers.getMapper(IUserMapper.class);
    User toUser(final CreateUserRequestDto dto);
    User toUpdateUser(final UpdateUserRequestDto dto);
}
