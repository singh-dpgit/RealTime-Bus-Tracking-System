package com.radharath.Radharath_backend.controller;

import com.radharath.Radharath_backend.repository.BusRepository;
import com.radharath.Radharath_backend.repository.RouteRepository;
import com.radharath.Radharath_backend.repository.StopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/stats")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class StatsController {

    private final BusRepository busRepository;
    private final RouteRepository routeRepository;
    private final StopRepository stopRepository; // Pehle se bani repository yahan use ho rahi hai

    @GetMapping
    public Map<String, Long> getSystemStats() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("totalBuses", busRepository.count());
        stats.put("busesOnRoute", busRepository.count());
        stats.put("totalRoutes", routeRepository.count());
        stats.put("activeStops", stopRepository.count()); // Yeh ab database ki stops table se real count uthayega
        return stats;
    }
}