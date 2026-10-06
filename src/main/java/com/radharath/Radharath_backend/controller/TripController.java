package com.radharath.Radharath_backend.controller;

import com.radharath.Radharath_backend.dto.TripRequest;
import com.radharath.Radharath_backend.dto.TripResponse;
import com.radharath.Radharath_backend.service.TripService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trips")
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    @PostMapping("/start")
    public ResponseEntity<TripResponse> startTrip(@Valid @RequestBody TripRequest request) {
        return new ResponseEntity<>(tripService.startTrip(request), HttpStatus.CREATED);
    }

    @PutMapping("/{tripId}/end")
    public ResponseEntity<TripResponse> endTrip(@PathVariable Long tripId) {
        return ResponseEntity.ok(tripService.endTrip(tripId));
    }
}