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
public class RouteStopRequest {

    @NotNull(message = "Stop ID is required")
    private Long stopId;

    @NotNull(message = "Stop order is required")
    private Integer stopOrder;
}