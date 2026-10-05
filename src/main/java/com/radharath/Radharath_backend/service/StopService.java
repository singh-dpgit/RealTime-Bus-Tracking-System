package com.radharath.Radharath_backend.service;

import com.radharath.Radharath_backend.dto.StopRequest;
import com.radharath.Radharath_backend.dto.StopResponse;
import com.radharath.Radharath_backend.entity.Stop;
import com.radharath.Radharath_backend.exception.ResourceNotFoundException;
import com.radharath.Radharath_backend.repository.StopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StopService {

    private final StopRepository stopRepository;

    // Entity to DTO Converter Helper Method
    private StopResponse mapToStopResponse(Stop stop) {
        return StopResponse.builder()
                .id(stop.getId())
                .name(stop.getName())
                .latitude(stop.getLatitude())
                .longitude(stop.getLongitude())
                .build();
    }

    // 1. Add new Stop
    public StopResponse createStop(StopRequest request) {
        Stop stop = Stop.builder()
                .name(request.getName())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .build();

        Stop savedStop = stopRepository.save(stop);
        return mapToStopResponse(savedStop);
    }

    // 2. Get all Stops
    public List<StopResponse> getAllStops() {
        List<Stop> stops = stopRepository.findAll();
        return stops.stream()
                .map(this::mapToStopResponse)
                .collect(Collectors.toList());
    }

    // 3. Get Stop by ID
    public StopResponse getStopById(Long id) {
        Stop stop = stopRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stop not found with ID: " + id));
        return mapToStopResponse(stop);
    }
}