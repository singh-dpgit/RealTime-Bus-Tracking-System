package com.radharath.Radharath_backend.dto;

import com.radharath.Radharath_backend.entity.BusType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BusRequest {

    @NotBlank(message = "Bus number is required")
    private String busNumber;

    @NotBlank(message = "Registration number is required")
    private String registrationNumber;

    @NotNull(message = "Bus type is required")
    private BusType busType;

    @NotNull(message = "Capacity is required")
    private Integer capacity;

    @NotNull(message = "Registered By (User ID) is required")
    private Long registeredById;
}