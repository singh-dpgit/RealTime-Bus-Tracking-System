package com.radharath.Radharath_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RouteStopResponse {
    private Long routeStopId;
    private Long stopId;
    private String stopName;
    private Integer stopOrder;
}