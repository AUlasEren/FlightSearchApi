package com.ulas.mapper;

import com.ulas.dto.request.CreateAirportRequestDto;
import com.ulas.dto.request.CreateFlightsRequestDto;
import com.ulas.dto.request.UpdateAirportRequestDto;
import com.ulas.dto.request.UpdateFlightsRequestDto;
import com.ulas.repository.entity.Airport;
import com.ulas.repository.entity.Flights;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAirportMapper {
    IAirportMapper INSTANCE= Mappers.getMapper(IAirportMapper.class);
    Airport toAirport(final CreateAirportRequestDto dto);
    Airport toUpdatedAirport(final UpdateAirportRequestDto dto);
}
