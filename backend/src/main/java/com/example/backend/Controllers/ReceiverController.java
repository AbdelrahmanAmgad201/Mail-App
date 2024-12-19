package com.example.backend.Controllers;

import com.example.backend.DTO.ReceiverDTO;
import com.example.backend.DTO.FilterDTO;
import com.example.backend.DTO.RegistrationRequest;
import com.example.backend.Services.ReceiverService;
import com.example.backend.Services.SentEmailService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.backend.Services.SortEmailsService;
import com.example.backend.Services.FilterEmailService;
import com.example.backend.Services.AllEmailService;

import java.util.List;

@RestController
@RequestMapping("/api/receivers")
@CrossOrigin(origins = "http://localhost:5173")
public class ReceiverController {

    @Autowired
    private final ReceiverService receiverService;
    private final SortEmailsService sortEmailsService;
    private final FilterEmailService filterEmailService;
    private final AllEmailService allEmailService;
    private final SentEmailService sentEmailService;

    public ReceiverController(
        ReceiverService receiverService,
        SortEmailsService sortEmailsService,
        FilterEmailService filterEmailService,
        AllEmailService allEmailService,
        SentEmailService sentEmailService
    ){
        this.receiverService = receiverService;
        this.sortEmailsService = sortEmailsService;
        this.filterEmailService = filterEmailService;
        this.allEmailService = allEmailService;
        this.sentEmailService = sentEmailService;
    }

    @GetMapping("/inbox/{userId}")
    public ResponseEntity<List<ReceiverDTO>> getInboxEmails(@PathVariable Long userId) {
        List<ReceiverDTO> emails = receiverService.getInboxEmails(userId);
        return ResponseEntity.ok(filterEmailService.removeTrash(sortEmailsService.sort(emails, "date"), userId));
    }

    @GetMapping("/trash/{userId}")
    public ResponseEntity<List<ReceiverDTO>> getTrashEmails(@PathVariable Long userId) {
        List<ReceiverDTO> emails = allEmailService.getAllEmails(userId);
        return ResponseEntity.ok(filterEmailService.getTrash(sortEmailsService.sort(emails, "date"), userId));
    }

    @GetMapping("/sent/{userId}")
    public ResponseEntity<List<ReceiverDTO>> getSentEmails(@PathVariable Long userId) {
        List<ReceiverDTO> sentEmails = sentEmailService.getSentEmails(userId);
        return ResponseEntity.ok(filterEmailService.removeTrash(sortEmailsService.sort(sentEmails, "date"), userId));
    }
    
    @GetMapping("/starred/{userId}")
    public ResponseEntity<List<ReceiverDTO>> getStarredEmails(@PathVariable Long userId) {
        List<ReceiverDTO> emails = allEmailService.getAllEmails(userId);
        return ResponseEntity.ok(
            filterEmailService.getStarred(
            filterEmailService.removeTrash(
                sortEmailsService.sort(emails, "date"), userId), userId));
    }

    @PostMapping("/filter/{userId}")
    public ResponseEntity<?> signup(@RequestBody FilterDTO filterDTO) {
        try {
            List<ReceiverDTO> emails = allEmailService.getAllEmails(filterDTO.getUserId());
            return ResponseEntity.ok(filterEmailService.filter(emails, filterDTO));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}