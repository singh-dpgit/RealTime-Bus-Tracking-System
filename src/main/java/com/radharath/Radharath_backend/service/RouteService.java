package com.radharath.Radharath_backend.service;

import com.radharath.Radharath_backend.dto.RouteRequest;
import com.radharath.Radharath_backend.dto.RouteResponse;
import com.radharath.Radharath_backend.entity.Route;
import com.radharath.Radharath_backend.exception.ResourceNotFoundException;
import com.radharath.Radharath_backend.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final RouteRepository routeRepository;

    private RouteResponse mapToRouteResponse(Route route) {
        return RouteResponse.builder()
                .id(route.getId())
                .routeNumber(route.getRouteNumber())
                .source(route.getSource())
                .destination(route.getDestination())
                .build();
    }

    // 1. Create Route
    public RouteResponse createRoute(RouteRequest request) {
        // Check if route number already exists
        if (routeRepository.existsByRouteNumber(request.getRouteNumber())) {
            throw new RuntimeException("Route number " + request.getRouteNumber() + " already exists!");
        }

        Route route = Route.builder()
                .routeNumber(request.getRouteNumber())
                .source(request.getSource())
                .destination(request.getDestination())
                .build();

        Route savedRoute = routeRepository.save(route);
        return mapToRouteResponse(savedRoute);
    }

    // 2. Get all Routes
    public List<RouteResponse> getAllRoutes() {
        return routeRepository.findAll().stream()
                .map(this::mapToRouteResponse)
                .collect(Collectors.toList());
    }

    // 3. Get Route by ID
    public RouteResponse getRouteById(Long id) {
        Route route = routeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Route not found with ID: " + id));
        return mapToRouteResponse(route);
    }
}