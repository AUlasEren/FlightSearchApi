package com.ulas.controller;

import com.ulas.dto.request.CreateAirportRequestDto;
import com.ulas.dto.request.CreateFlightsRequestDto;
import com.ulas.dto.request.UpdateAirportRequestDto;
import com.ulas.dto.request.UpdateFlightsRequestDto;
import com.ulas.repository.entity.Airport;
import com.ulas.repository.entity.Flights;
import com.ulas.service.AirportService;
import com.ulas.service.FlightsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ulas.constants.EndPoints.*;
import static com.ulas.constants.EndPoints.GET_ALL;

@RestController
@RequiredArgsConstructor
@RequestMapping(AIRPORT)
public class AirportController {
    private final AirportService airportService;
    @PostMapping(CREATE)

    public ResponseEntity<Airport> createAirport(@RequestBody CreateAirportRequestDto dto){
        return ResponseEntity.ok(airportService.createAirport(dto));
    }
    @PutMapping(UPDATE)
    public ResponseEntity<Airport> updateAirport(@RequestBody UpdateAirportRequestDto dto){
        return ResponseEntity.ok(airportService.updateAirport(dto));
    }

    @DeleteMapping(DELETE)
    public ResponseEntity<Airport> deleteFlights(Long id){
        return ResponseEntity.ok(airportService.deleteAirport(id));
    }

    @GetMapping(GET_ALL)
    public ResponseEntity<List<Airport>> getAll(){
        return ResponseEntity.ok(airportService.findAll());
    }
}
