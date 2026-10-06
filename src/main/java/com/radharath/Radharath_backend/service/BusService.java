package com.radharath.Radharath_backend.service;

import com.radharath.Radharath_backend.dto.BusRequest;
import com.radharath.Radharath_backend.dto.BusResponse;
import com.radharath.Radharath_backend.entity.Bus;
import com.radharath.Radharath_backend.entity.BusStatus;
import com.radharath.Radharath_backend.entity.User;
import com.radharath.Radharath_backend.exception.ResourceNotFoundException;
import com.radharath.Radharath_backend.repository.BusRepository;
import com.radharath.Radharath_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BusService {

    private final BusRepository busRepository;
    private final UserRepository userRepository;

    // Username parameter add kiya gaya hai
    public BusResponse registerBus(BusRequest request, String currentUsername) {
        if (busRepository.existsByBusNumber(request.getBusNumber())) {
            throw new RuntimeException("Bus Number already exists!");
        }
        if (busRepository.existsByRegistrationNumber(request.getRegistrationNumber())) {
            throw new RuntimeException("Registration Number already exists!");
        }

        // Frontend ki ID par bharosa karne ke bajay, token se direct user nikala (Highly Secure)
        User driver = userRepository.findByEmail(currentUsername)
                .orElseThrow(() -> new ResourceNotFoundException("Logged in driver not found in database"));

        Bus bus = Bus.builder()
                .busNumber(request.getBusNumber())
                .registrationNumber(request.getRegistrationNumber())
                .busType(request.getBusType())
                .capacity(request.getCapacity())
                .busStatus(BusStatus.PENDING)
                .registeredBy(driver) // Seedha mapped driver assign kar diya
                .build();

        Bus savedBus = busRepository.save(bus);
        return mapToResponse(savedBus);
    }

    public List<BusResponse> getAllBuses() {
        return busRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private BusResponse mapToResponse(Bus bus) {
        return BusResponse.builder()
                .id(bus.getId())
                .busNumber(bus.getBusNumber())
                .registrationNumber(bus.getRegistrationNumber())
                .busType(bus.getBusType())
                .capacity(bus.getCapacity())
                .busStatus(bus.getBusStatus())
                .registeredByUserName(bus.getRegisteredBy().getName())
                .build();
    }

    public List<BusResponse> getBusesByDriver(String username) {
        User driver = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        List<Bus> driverBuses = busRepository.findByRegisteredBy(driver);

        return driverBuses.stream()
                .map(this::mapToResponse)
                .toList();
    }
}