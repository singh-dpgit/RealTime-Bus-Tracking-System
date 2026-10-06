package com.radharath.Radharath_backend.repository;

import com.radharath.Radharath_backend.entity.Bus;
import com.radharath.Radharath_backend.entity.BusLocation;
import com.radharath.Radharath_backend.entity.BusStatus;
import com.radharath.Radharath_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BusRepository extends JpaRepository<Bus,Long> {
    Optional<Bus> findByBusNumber(String busNumber);                 // passenger search: bus number se bus
    boolean existsByBusNumber(String busNumber);                     // register: bus number duplicate toh nahi
    boolean existsByRegistrationNumber(String registrationNumber);   // register: registration duplicate toh nahi
    List<Bus> findByBusStatus(BusStatus status);                        // admin: PENDING wali buses ki list
    List<Bus> findByRegisteredBy(User user);
    
}
