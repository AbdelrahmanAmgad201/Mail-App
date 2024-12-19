package com.example.backend.Controllers;

import com.example.backend.DTO.ReceiverDTO;
import com.example.backend.Services.SentEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emails")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class SentEmailController {

    // private final SentEmailService sentEmailService;

    // @GetMapping("/sent/{userId}")
    // public ResponseEntity<List<ReceiverDTO>> getSentEmails(@PathVariable Long userId) {
    //     List<ReceiverDTO> sentEmails = sentEmailService.getSentEmails(userId);
    //     return ResponseEntity.ok(sentEmails);
    // }
}