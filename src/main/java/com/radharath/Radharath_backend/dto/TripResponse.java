package com.radharath.Radharath_backend.dto;

import com.radharath.Radharath_backend.entity.TripStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripResponse {
    private Long id;
    private String busNumber;
    private String driverName;
    private String routeNumber;
    private String source;
    private String destination;
    private TripStatus status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}