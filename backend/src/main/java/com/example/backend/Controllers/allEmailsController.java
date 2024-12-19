package com.example.backend.Controllers;

import com.example.backend.DTO.ReceiverDTO;
import com.example.backend.Services.AllEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/emails/all")
public class allEmailsController {
    private final AllEmailService allEmailService;

    public allEmailsController(AllEmailService allEmailService) {
        this.allEmailService = allEmailService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReceiverDTO>> getAllEmailsForUser(@PathVariable Long userId) {
        List<ReceiverDTO> emails = allEmailService.getAllEmails(userId);
        return ResponseEntity.ok(emails);
    }
}
