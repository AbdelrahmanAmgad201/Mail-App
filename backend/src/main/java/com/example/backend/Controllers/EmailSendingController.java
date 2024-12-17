package com.example.backend.Controllers;

import com.example.backend.Services.EmailSendingService;
import com.example.backend.Services.SendEmailRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/emails")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class EmailSendingController {

    private final EmailSendingService emailSendingService;

    @PostMapping("/send")
    public ResponseEntity<?> sendEmail(@RequestBody SendEmailRequestDTO emailRequest) {
        try {
            return ResponseEntity.ok(emailSendingService.sendEmail(emailRequest));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}