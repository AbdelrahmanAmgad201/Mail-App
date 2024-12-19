package com.example.backend.Controllers;

import com.example.backend.Entities.Email;
import com.example.backend.Entities.Starred;
import com.example.backend.Services.StarredService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/starred")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class StarredController {
    private final StarredService starredService;

    @PostMapping("/star/{userId}/{emailId}")
    public ResponseEntity<?> starEmail(
            @PathVariable Long userId,
            @PathVariable Long emailId
    ) {
        try {
            Optional<Starred> starred = starredService.starEmail(userId, emailId);

            if (starred.isPresent()) {
                return ResponseEntity.ok().body(starred.get());
            } else {
                return ResponseEntity.ok().body("Email unstarred");
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
