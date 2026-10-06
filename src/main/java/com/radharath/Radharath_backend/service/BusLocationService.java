package com.radharath.Radharath_backend.service;

import com.radharath.Radharath_backend.dto.LocationRequest;
import com.radharath.Radharath_backend.dto.LocationResponse;
import com.radharath.Radharath_backend.entity.BusLocation;
import com.radharath.Radharath_backend.entity.Trip;
import com.radharath.Radharath_backend.entity.TripStatus;
import com.radharath.Radharath_backend.exception.ResourceNotFoundException;
import com.radharath.Radharath_backend.repository.BusLocationRepository;
import com.radharath.Radharath_backend.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BusLocationService {

    private final BusLocationRepository busLocationRepository;
    private final TripRepository tripRepository;

    // 1. Driver har kuch seconds mein ye hit karega
    public LocationResponse recordLocation(LocationRequest request) {
        Trip trip = tripRepository.findById(request.getTripId())
                .orElseThrow(() -> new ResourceNotFoundException("Trip not found"));

        if (trip.getStatus() != TripStatus.ACTIVE) {
            throw new RuntimeException("Cannot record location. Trip is not active.");
        }

        BusLocation location = BusLocation.builder()
                .trip(trip)
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .recordedAt(LocalDateTime.now())
                .build();

        BusLocation savedLocation = busLocationRepository.save(location);

        return mapToResponse(savedLocation);
    }

    // 2. User map par bus dekhne ke liye ye hit karega (Latest location)
    public LocationResponse getLatestLocation(Long tripId) {
        BusLocation location = busLocationRepository.findTopByTripIdOrderByRecordedAtDesc(tripId)
                .orElseThrow(() -> new ResourceNotFoundException("No location found for this trip"));

        return mapToResponse(location);
    }

    // 3. Poora route path draw karne ke liye (Location History)
    public List<LocationResponse> getTripHistory(Long tripId) {
        return busLocationRepository.findByTripIdOrderByRecordedAtAsc(tripId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private LocationResponse mapToResponse(BusLocation location) {
        return LocationResponse.builder()
                .id(location.getId())
                .tripId(location.getTrip().getId())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .recordedAt(location.getRecordedAt())
                .build();
    }
}