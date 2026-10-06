package com.radharath.Radharath_backend.service;

import com.radharath.Radharath_backend.dto.RouteStopRequest;
import com.radharath.Radharath_backend.dto.RouteStopResponse;
import com.radharath.Radharath_backend.entity.Route;
import com.radharath.Radharath_backend.entity.RouteStop;
import com.radharath.Radharath_backend.entity.Stop;
import com.radharath.Radharath_backend.exception.ResourceNotFoundException;
import com.radharath.Radharath_backend.repository.RouteRepository;
import com.radharath.Radharath_backend.repository.RouteStopRepository;
import com.radharath.Radharath_backend.repository.StopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouteStopService {

    private final RouteStopRepository routeStopRepository;
    private final RouteRepository routeRepository;
    private final StopRepository stopRepository;

    // 1. Route mein multiple stops add karna
    @Transactional
    public List<RouteStopResponse> addStopsToRoute(Long routeId, List<RouteStopRequest> stopRequests) {

        // Pehle check karo Route exist karta hai ya nahi
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new ResourceNotFoundException("Route not found with ID: " + routeId));

        // Har request se RouteStop entity banao
        List<RouteStop> routeStops = stopRequests.stream().map(request -> {
            Stop stop = stopRepository.findById(request.getStopId())
                    .orElseThrow(() -> new ResourceNotFoundException("Stop not found with ID: " + request.getStopId()));

            return RouteStop.builder()
                    .route(route)
                    .stop(stop)
                    .stopOrder(request.getStopOrder())
                    .build();
        }).collect(Collectors.toList());

        // Sabko ek sath database mein save karo
        List<RouteStop> savedRouteStops = routeStopRepository.saveAll(routeStops);

        // Response DTO mein convert karke return karo
        return savedRouteStops.stream().map(rs -> RouteStopResponse.builder()
                .routeStopId(rs.getId())
                .stopId(rs.getStop().getId())
                .stopName(rs.getStop().getName())
                .stopOrder(rs.getStopOrder())
                .build()).collect(Collectors.toList());
    }

    // 2. Ek Route ke saare stops order mein get karna
    public List<RouteStopResponse> getStopsByRoute(Long routeId) {
        List<RouteStop> routeStops = routeStopRepository.findByRouteIdOrderByStopOrderAsc(routeId);

        return routeStops.stream().map(rs -> RouteStopResponse.builder()
                .routeStopId(rs.getId())
                .stopId(rs.getStop().getId())
                .stopName(rs.getStop().getName())
                .stopOrder(rs.getStopOrder())
                .build()).collect(Collectors.toList());
    }
}