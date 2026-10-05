package com.radharath.Radharath_backend.repository;

import com.radharath.Radharath_backend.entity.BusLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BusLocationRepository extends JpaRepository<BusLocation,Long> {
    Optional<BusLocation> findTopByTripIdOrderByRecordedAtDesc(Long tripId); // map marker: sabse nayi location
    List<BusLocation> findByTripIdOrderByRecordedAtAsc(Long tripId);         // trip ka poora history, purani se nayi
}

