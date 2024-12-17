package com.example.backend.Controllers;

import com.example.backend.DTO.RegistrationRequest;
import com.example.backend.Services.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/registration")
@CrossOrigin(origins = "http://localhost:5173")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody RegistrationRequest registrationRequest) {
        try {
            return ResponseEntity.ok(registrationService.login(registrationRequest));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid credentials");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody RegistrationRequest registrationRequest) {
        try {
            return ResponseEntity.ok(Long.toString(registrationService.signup(registrationRequest)));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
