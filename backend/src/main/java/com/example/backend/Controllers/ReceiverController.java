package com.example.backend.Controllers;

import com.example.backend.Services.ReceiverDTO;
import com.example.backend.Services.ReceiverService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/receivers")
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:5173")
public class ReceiverController {

    @Autowired
    private final ReceiverService receiverService;

    @GetMapping("/inbox/{userId}")
    public ResponseEntity<List<ReceiverDTO>> getInboxEmails(@PathVariable Long userId) {
        List<ReceiverDTO> emails = receiverService.getInboxEmails(userId);
        return ResponseEntity.ok(emails);
    }

//    @PutMapping("/{receiverId}/read")
//    public ResponseEntity<ReceiverDTO> markEmailAsRead(@PathVariable Long receiverId) {
//        ReceiverDTO updatedReceiver = receiverService.markEmailAsRead(receiverId);
//        return ResponseEntity.ok(updatedReceiver);
//    }
//
//    @PutMapping("/{receiverId}/trash")
//    public ResponseEntity<ReceiverDTO> moveEmailToTrash(@PathVariable Long receiverId) {
//        ReceiverDTO updatedReceiver = receiverService.moveEmailToTrash(receiverId);
//        return ResponseEntity.ok(updatedReceiver);
//    }
}