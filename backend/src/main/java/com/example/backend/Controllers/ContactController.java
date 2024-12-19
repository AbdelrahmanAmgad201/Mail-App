package com.example.backend.Controllers;

import com.example.backend.DTO.ContactDTO;
import com.example.backend.Services.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ContactController {

    private final ContactService contactService;

    @PostMapping("/create")
    public ResponseEntity<ContactDTO> createContact(
            @RequestParam Long ownerId,
            @RequestParam String contactName,
            @RequestBody List<String> emailAddresses
    ) {
        ContactDTO createdContact = contactService.createContact(ownerId, contactName, emailAddresses);
        return ResponseEntity.ok(createdContact);
    }

    @GetMapping("/user/{ownerId}")
    public ResponseEntity<List<ContactDTO>> getAllContacts(@PathVariable Long ownerId) {
        List<ContactDTO> contacts = contactService.getAllContacts(ownerId);
        return ResponseEntity.ok(contacts);
    }
}