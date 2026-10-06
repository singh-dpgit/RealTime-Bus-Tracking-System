package com.radharath.Radharath_backend.controller;

import com.radharath.Radharath_backend.dto.BusRequest;
import com.radharath.Radharath_backend.dto.BusResponse;
import com.radharath.Radharath_backend.service.BusService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buses")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BusController {

    private final BusService busService;

    // Final Deployment Ready: Driver ki details token se automatically extract hongi
    @PostMapping
    public ResponseEntity<BusResponse> registerBus(@Valid @RequestBody BusRequest request, Authentication authentication) {
        String currentUsername = authentication.getName(); // JWT token se email nikala
        return new ResponseEntity<>(busService.registerBus(request, currentUsername), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BusResponse>> getAllBuses() {
        return ResponseEntity.ok(busService.getAllBuses());
    }

    @GetMapping("/my-buses")
    public ResponseEntity<List<BusResponse>> getMyBuses(Authentication authentication) {
        String currentUsername = authentication.getName();
        return ResponseEntity.ok(busService.getBusesByDriver(currentUsername));
    }
}