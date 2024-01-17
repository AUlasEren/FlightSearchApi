package com.ulas.repository;

import com.ulas.repository.entity.Flights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IFlightsRepository extends JpaRepository<Flights,Long> {
    List<Flights> findByDepartureAirportAndArrivalAirportAndDepartureTime(
            String departureAirport, String arrivalAirport, LocalDateTime departureTime);

}
