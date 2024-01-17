package com.ulas.repository;

import com.ulas.repository.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAirportRepository extends JpaRepository<Airport,Long> {
    Optional<Airport> findByAirportName(String name);
}
