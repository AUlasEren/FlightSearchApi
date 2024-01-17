package com.ulas.service;

import com.ulas.dto.request.CreateFlightsRequestDto;
import com.ulas.dto.request.UpdateFlightsRequestDto;
import com.ulas.exception.EErrorType;
import com.ulas.exception.UserManagerException;
import com.ulas.mapper.IFlightsMapper;
import com.ulas.repository.IAirportRepository;
import com.ulas.repository.IFlightsRepository;
import com.ulas.repository.entity.Airport;
import com.ulas.repository.entity.Flights;
import com.ulas.repository.enums.EStatus;
import com.ulas.utilty.ServiceManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FlightsService extends ServiceManager<Flights,Long> {
    private  final IFlightsRepository iFlightsRepository;

    public FlightsService(IFlightsRepository iFlightsRepository) {
        super(iFlightsRepository);
        this.iFlightsRepository = iFlightsRepository;

    }

    public Flights createFlights(CreateFlightsRequestDto dto) {
        Flights flights = IFlightsMapper.INSTANCE.toFlights(dto);
        save(flights);
        return flights;
    }

    public Flights updateFlights(UpdateFlightsRequestDto dto) {
        Optional<Flights> existFlights = iFlightsRepository.findById(dto.getId());
        if (existFlights.isEmpty() || existFlights.get().getStatus().equals(EStatus.DELETED))
            throw new UserManagerException(EErrorType.FLIGHTS_NOT_FOUND);
        Flights updatedFlights = IFlightsMapper.INSTANCE.toUpdatedFlights(dto);
        updatedFlights.setId(existFlights.get().getId());
        updatedFlights.setCreateDate(existFlights.get().getCreateDate());
        update(updatedFlights);
        return updatedFlights;
    }
    public Flights deleteFlights(Long id) {
        Optional<Flights> existsFlight = iFlightsRepository.findById(id);
        if (existsFlight.isEmpty() || existsFlight.get().getStatus().equals(EStatus.DELETED))
            throw new UserManagerException(EErrorType.FLIGHTS_NOT_FOUND);
        existsFlight.get().setStatus(EStatus.DELETED);
        update(existsFlight.get());
        return existsFlight.get();
    }
    public List<Flights> findFlights(String departureAirport, String arrivalAirport,
                                     LocalDateTime departureTime, LocalDateTime returnTime) {
        // Gidiş uçuşları
        List<Flights> departureFlights = iFlightsRepository.findByDepartureAirportAndArrivalAirportAndDepartureTime(
                departureAirport, arrivalAirport, departureTime);

        // Dönüş uçuşları (eğer returnTime varsa)
        Stream<Flights> returnFlightsStream = returnTime == null ?
                Stream.empty() :
                iFlightsRepository.findByDepartureAirportAndArrivalAirportAndDepartureTime(
                        arrivalAirport, departureAirport, returnTime).stream();

        // İki listeyi birleştir
        return Stream.concat(departureFlights.stream(), returnFlightsStream)
                .collect(Collectors.toList());
    }
    @Scheduled(cron = "0 0 1 * * ?")
    public void fetchAndSaveMockFlights() {
        List<Flights> mockFlights = getMockFlights();
        mockFlights.forEach(this::save);
    }

    private List<Flights> getMockFlights() {
        // Mock uçuş bilgileri oluşturun
        return List.of(
                Flights.builder()
                        .departureAirport("IST")
                        .arrivalAirport("JFK")
                        .departureTime(LocalDateTime.of(2024, 1, 1, 10, 0))
                        .returnTime(LocalDateTime.of(2024, 1, 1, 14, 0))
                        .price(500.0)
                        .status(EStatus.ACTIVE)
                        .build(),
                Flights.builder()
                        .departureAirport("LAX")
                        .arrivalAirport("LHR")
                        .departureTime(LocalDateTime.of(2024, 1, 2, 11, 0))
                        .returnTime(LocalDateTime.of(2024, 1, 2, 15, 0))
                        .price(600.0)
                        .status(EStatus.ACTIVE)
                        .build()
                // Daha fazla mock uçuş eklenebilir
        );
    }
}
