package com.radharath.Radharath_backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TripRequest {

    @NotNull(message = "Bus ID is required")
    private Long busId;

    @NotNull(message = "Driver (User ID) is required")
    private Long driverId;

    @NotNull(message = "Route ID is required")
    private Long routeId;
}