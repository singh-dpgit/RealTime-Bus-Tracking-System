package com.radharath.Radharath_backend.controller;

import com.radharath.Radharath_backend.dto.LocationRequest;
import com.radharath.Radharath_backend.dto.LocationResponse;
import com.radharath.Radharath_backend.service.BusLocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
public class BusLocationController {

    private final BusLocationService locationService;

    // Driver ka app GPS data yahan bhejega
    @PostMapping
    public ResponseEntity<LocationResponse> recordLocation(@Valid @RequestBody LocationRequest request) {
        return new ResponseEntity<>(locationService.recordLocation(request), HttpStatus.CREATED);
    }

    // User app current location yahan se lega
    @GetMapping("/trip/{tripId}/latest")
    public ResponseEntity<LocationResponse> getLatestLocation(@PathVariable Long tripId) {
        return ResponseEntity.ok(locationService.getLatestLocation(tripId));
    }

    // Trip ki poori history
    @GetMapping("/trip/{tripId}/history")
    public ResponseEntity<List<LocationResponse>> getTripHistory(@PathVariable Long tripId) {
        return ResponseEntity.ok(locationService.getTripHistory(tripId));
    }
}