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
public class StopResponse {

    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;
}