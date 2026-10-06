package com.radharath.Radharath_backend.service;

import com.radharath.Radharath_backend.dto.TripRequest;
import com.radharath.Radharath_backend.dto.TripResponse;
import com.radharath.Radharath_backend.entity.*;
import com.radharath.Radharath_backend.exception.ResourceNotFoundException;
import com.radharath.Radharath_backend.repository.BusRepository;
import com.radharath.Radharath_backend.repository.RouteRepository;
import com.radharath.Radharath_backend.repository.TripRepository;
import com.radharath.Radharath_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;
    private final BusRepository busRepository;
    private final UserRepository userRepository;
    private final RouteRepository routeRepository;

    @Transactional
    public TripResponse startTrip(TripRequest request) {
        // Validation: Ensure Bus, Driver, and Route exist
        Bus bus = busRepository.findById(request.getBusId())
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found"));
        User driver = userRepository.findById(request.getDriverId())
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found"));
        Route route = routeRepository.findById(request.getRouteId())
                .orElseThrow(() -> new ResourceNotFoundException("Route not found"));

        // Validation: Driver must not have another active trip
        if (tripRepository.existsByDriverIdAndStatus(driver.getId(), TripStatus.ACTIVE)) {
            throw new RuntimeException("Driver is already on an active trip");
        }

        // Validation: Bus must not be on another active trip
        if (tripRepository.existsByBusIdAndStatus(bus.getId(), TripStatus.ACTIVE)) {
            throw new RuntimeException("Bus is already on an active trip");
        }

        // Create new Trip
        Trip trip = Trip.builder()
                .bus(bus)
                .driver(driver)
                .route(route)
                .startTime(LocalDateTime.now())
                .status(TripStatus.ACTIVE)
                .build();

        Trip savedTrip = tripRepository.save(trip);

        // Update Bus Status
        bus.setBusStatus(BusStatus.ACTIVE);
        busRepository.save(bus);

        return mapToResponse(savedTrip);
    }

    @Transactional
    public TripResponse endTrip(Long tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new ResourceNotFoundException("Trip not found"));

        trip.setStatus(TripStatus.COMPLETED);
        trip.setEndTime(LocalDateTime.now());

        // Update Bus status back to PENDING/INACTIVE
        Bus bus = trip.getBus();
        bus.setBusStatus(BusStatus.PENDING);
        busRepository.save(bus);

        return mapToResponse(tripRepository.save(trip));
    }

    private TripResponse mapToResponse(Trip trip) {
        return TripResponse.builder()
                .id(trip.getId())
                .busNumber(trip.getBus().getBusNumber())
                .driverName(trip.getDriver().getName())
                .routeNumber(trip.getRoute().getRouteNumber())
                .source(trip.getRoute().getSource())
                .destination(trip.getRoute().getDestination())
                .status(trip.getStatus())
                .startTime(trip.getStartTime())
                .endTime(trip.getEndTime())
                .build();
    }
}