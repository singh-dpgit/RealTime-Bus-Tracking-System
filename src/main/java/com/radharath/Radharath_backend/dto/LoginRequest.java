package com.radharath.Radharath_backend.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username; // Ya phone number
    private String password;
}