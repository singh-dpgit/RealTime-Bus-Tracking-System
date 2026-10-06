package com.radharath.Radharath_backend.controller;

import com.radharath.Radharath_backend.dto.LoginRequest;
import com.radharath.Radharath_backend.entity.Role;
import com.radharath.Radharath_backend.entity.User;
import com.radharath.Radharath_backend.repository.UserRepository;
import com.radharath.Radharath_backend.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/driver")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> loginDriver(@RequestBody LoginRequest request) {
        // Yahan aap apna database check kar sakte hain ki driver/admin database mein exist karta hai ya nahi.
        // Filhal ke liye agar username khali nahi hai toh token generate kar rahe hain:

        if (request.getUsername() != null && !request.getUsername().isEmpty()) {
            // Token generate ho raha hai
            String token = jwtUtil.generateToken(request.getUsername());

            // Response mein token bhej rahe hain
            return ResponseEntity.ok(Map.of(
                    "message", "Login successful!",
                    "token", token
            ));
        }

        return ResponseEntity.status(401).body("Invalid credentials");
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerDriver(@RequestBody Map<String, String> driverData) {
        String email = driverData.get("email");

        // Check if email already exists
        if (userRepository.findByEmail(email).isPresent()) {
            return ResponseEntity.badRequest().body("Email already exists in the database.");
        }

        // By default role ko DRIVER set kar diya hai
        User newDriver = User.builder()
                .name(driverData.get("name"))
                .email(email)
                .password(passwordEncoder.encode(driverData.get("password")))
                .role(Role.DRIVER) // <-- Default role fixed
                .build();

        userRepository.save(newDriver);

        return ResponseEntity.ok(Map.of("message", "Driver registered successfully."));
    }
}