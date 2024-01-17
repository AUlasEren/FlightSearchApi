package com.ulas.mapper;

import com.ulas.dto.request.CreateFlightsRequestDto;
import com.ulas.dto.request.UpdateFlightsRequestDto;
import com.ulas.repository.entity.Flights;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IFlightsMapper {
    IFlightsMapper INSTANCE= Mappers.getMapper(IFlightsMapper.class);

    Flights toFlights(final CreateFlightsRequestDto dto);
    Flights toUpdatedFlights(final UpdateFlightsRequestDto dto);
}
