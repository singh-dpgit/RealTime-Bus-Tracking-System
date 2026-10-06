package com.radharath.Radharath_backend.dto;

import com.radharath.Radharath_backend.entity.BusStatus;
import com.radharath.Radharath_backend.entity.BusType;
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
public class BusResponse {
    private Long id;
    private String busNumber;
    private String registrationNumber;
    private BusType busType;
    private int capacity;
    private BusStatus busStatus;
    private String registeredByUserName;
}