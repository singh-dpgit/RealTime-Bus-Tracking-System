package com.radharath.Radharath_backend.controller;

import com.radharath.Radharath_backend.dto.StopRequest;
import com.radharath.Radharath_backend.dto.StopResponse;
import com.radharath.Radharath_backend.service.StopService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stops")
@RequiredArgsConstructor
public class StopController {

    private final StopService stopService;

    @PostMapping
    public ResponseEntity<StopResponse> createStop(@Valid @RequestBody StopRequest request) {
        StopResponse createdStop = stopService.createStop(request);
        return new ResponseEntity<>(createdStop, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StopResponse>> getAllStops() {
        return ResponseEntity.ok(stopService.getAllStops());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StopResponse> getStopById(@PathVariable Long id) {
        return ResponseEntity.ok(stopService.getStopById(id));
    }
}