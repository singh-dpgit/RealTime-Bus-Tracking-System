package com.radharath.Radharath_backend.repository;

import com.radharath.Radharath_backend.entity.BusLocation;
import com.radharath.Radharath_backend.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route,Long> {
    Optional<Route> findByRouteNumber(String routeNumber);   // route number (R-101) se route
    boolean existsByRouteNumber(String routeNumber);         // admin: route number duplicate toh nahi
}
