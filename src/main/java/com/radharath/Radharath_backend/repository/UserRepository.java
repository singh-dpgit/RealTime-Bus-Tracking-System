package com.radharath.Radharath_backend.repository;

import com.radharath.Radharath_backend.entity.BusLocation;
import com.radharath.Radharath_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email); //finding user by email
    boolean existsByEmail(String email); // checks already existing data


}
