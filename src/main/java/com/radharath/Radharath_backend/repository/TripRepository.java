package com.radharath.Radharath_backend.repository;

import com.radharath.Radharath_backend.entity.BusLocation;
import com.radharath.Radharath_backend.entity.Trip;
import com.radharath.Radharath_backend.entity.TripStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TripRepository extends JpaRepository<Trip,Long> {
    List<Trip> findByStatus(TripStatus status);                        // home page: saari ACTIVE trips (live buses)
    Optional<Trip> findByDriverIdAndStatus(Long driverId, TripStatus status); // driver ki chal rahi trip
    boolean existsByDriverIdAndStatus(Long driverId, TripStatus status);      // trip start se pehle: driver ki ACTIVE trip toh nahi
    boolean existsByBusIdAndStatus(Long busId, TripStatus status);            // trip start se pehle: bus pehle se chal toh nahi rahi

}
