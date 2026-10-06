package com.radharath.Radharath_backend.controller;

import com.radharath.Radharath_backend.dto.RouteStopRequest;
import com.radharath.Radharath_backend.dto.RouteStopResponse;
import com.radharath.Radharath_backend.service.RouteStopService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes/{routeId}/stops") // Path mein hi routeId hai
@RequiredArgsConstructor
public class RouteStopController {

    private final RouteStopService routeStopService;

    // POST: /api/routes/1/stops
    @PostMapping
    public ResponseEntity<List<RouteStopResponse>> addStopsToRoute(
            @PathVariable Long routeId,
            @Valid @RequestBody List<RouteStopRequest> stopRequests) {

        List<RouteStopResponse> responses = routeStopService.addStopsToRoute(routeId, stopRequests);
        return new ResponseEntity<>(responses, HttpStatus.CREATED);
    }

    // GET: /api/routes/1/stops
    @GetMapping
    public ResponseEntity<List<RouteStopResponse>> getStopsByRoute(@PathVariable Long routeId) {
        return ResponseEntity.ok(routeStopService.getStopsByRoute(routeId));
    }
}