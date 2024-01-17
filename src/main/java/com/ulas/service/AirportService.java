package com.ulas.service;

import com.ulas.dto.request.CreateAirportRequestDto;
import com.ulas.dto.request.CreateFlightsRequestDto;
import com.ulas.dto.request.UpdateAirportRequestDto;
import com.ulas.dto.request.UpdateFlightsRequestDto;
import com.ulas.exception.EErrorType;
import com.ulas.exception.UserManagerException;
import com.ulas.mapper.IAirportMapper;
import com.ulas.mapper.IFlightsMapper;
import com.ulas.repository.IAirportRepository;
import com.ulas.repository.entity.Airport;
import com.ulas.repository.entity.Flights;
import com.ulas.repository.enums.EStatus;
import com.ulas.utilty.JwtTokenManager;
import com.ulas.utilty.ServiceManager;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class AirportService extends ServiceManager<Airport,Long> {
    private  final IAirportRepository iAirportRepository;
    private final JwtTokenManager jwtTokenManager;

    public AirportService(IAirportRepository iAirportRepository, JwtTokenManager jwtTokenManager) {
        super(iAirportRepository);
        this.iAirportRepository = iAirportRepository;
        this.jwtTokenManager = jwtTokenManager;
    }

    public Airport createAirport(CreateAirportRequestDto dto) {
        Optional<Airport> existAirportName = iAirportRepository.findByAirportName(dto.getAirportName());
        if (existAirportName.isPresent())
            throw new IllegalStateException(dto.getAirportName()+" is used.");
        Airport airport = IAirportMapper.INSTANCE.toAirport(dto);
        save(airport);
        return airport;
    }

    public Airport updateAirport(UpdateAirportRequestDto dto) {
        Optional<Airport> existAirport = iAirportRepository.findById(dto.getId());
        if (existAirport.isEmpty() || existAirport.get().getStatus().equals(EStatus.DELETED))
            throw new UserManagerException(EErrorType.AIRPORT_NOT_FOUND);
      //  Optional<String> role = jwtTokenManager.getRoleFromToken(bearerToken);
      //  if (!role.equals("ADMIN")) { // Örnek olarak "ADMIN" rolünü kontrol ediyorum
         //   throw new UserManagerException(EErrorType.INVALID_TOKEN);
        //}
        Airport updatedAirport = IAirportMapper.INSTANCE.toUpdatedAirport(dto);
        updatedAirport.setId(existAirport.get().getId());
        updatedAirport.setCreateDate(existAirport.get().getCreateDate());
        update(updatedAirport);
        return updatedAirport;
    }

    public Airport deleteAirport(Long id) {
        Optional<Airport> existsAirport = iAirportRepository.findById(id);
        if (existsAirport.isEmpty() || existsAirport.get().getStatus().equals(EStatus.DELETED))
            throw new UserManagerException(EErrorType.AIRPORT_NOT_FOUND);
        existsAirport.get().setStatus(EStatus.DELETED);
        update(existsAirport.get());
        return existsAirport.get();
    }
}
