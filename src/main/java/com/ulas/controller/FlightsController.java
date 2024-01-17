package com.ulas.controller;

import com.ulas.dto.request.CreateFlightsRequestDto;
import com.ulas.dto.request.UpdateFlightsRequestDto;
import com.ulas.repository.entity.Flights;
import com.ulas.service.FlightsService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.ulas.constants.EndPoints.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(FLIGHTS)
public class FlightsController {
    private final FlightsService flightsService;

    @PostMapping(CREATE)
    public ResponseEntity<Flights> createFlights(@RequestBody CreateFlightsRequestDto dto) {
        return ResponseEntity.ok(flightsService.createFlights(dto));

    }

    @PutMapping(UPDATE)
    public ResponseEntity<Flights> updateFlights(@RequestBody UpdateFlightsRequestDto dto) {
        return ResponseEntity.ok(flightsService.updateFlights(dto));
    }

    @DeleteMapping(DELETE)
    public ResponseEntity<Flights> deleteFlights(Long id) {
        return ResponseEntity.ok(flightsService.deleteFlights(id));
    }

    @GetMapping(GET_ALL)
    public ResponseEntity<List<Flights>> getAll() {
        return ResponseEntity.ok(flightsService.findAll());
    }

    @GetMapping(FINDFLIGHTS)
    public List<Flights> findFlights(@RequestParam String departureAirport,
                                     @RequestParam String arrivalAirport,
                                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureTime,
                                     @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime returnTime) {
        return flightsService.findFlights(departureAirport, arrivalAirport, departureTime, returnTime);
    }

}