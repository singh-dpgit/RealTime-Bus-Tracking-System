package com.radharath.Radharath_backend.repository;

import com.radharath.Radharath_backend.entity.BusLocation;
import com.radharath.Radharath_backend.entity.Stop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StopRepository extends JpaRepository<Stop,Long> {
    List<Stop> findByNameContainingIgnoreCase(String name);  // From/To search: "how" likhne par Howrah mile
}

