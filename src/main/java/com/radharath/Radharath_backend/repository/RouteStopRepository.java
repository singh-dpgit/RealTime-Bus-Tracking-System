package com.radharath.Radharath_backend.repository;

import com.radharath.Radharath_backend.entity.BusLocation;
import com.radharath.Radharath_backend.entity.RouteStop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteStopRepository extends JpaRepository<RouteStop,Long> {
    List<RouteStop> findByRouteIdOrderByStopOrderAsc(Long routeId);  // is route ke stops, order 1, 2, 3 mein
    List<RouteStop> findByStopId(Long stopId);                        // is stop se kaunse routes guzarte hain

}
