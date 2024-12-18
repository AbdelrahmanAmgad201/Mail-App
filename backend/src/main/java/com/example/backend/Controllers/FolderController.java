package com.example.backend.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.backend.DTO.FolderRequest;
// import com.example.backend.Services.FolderService;

@RestController
@RequestMapping("/api/folders")
@CrossOrigin(origins = "http://localhost:5173")
public class FolderController {

    // private final FolderService folderService;

    // public FolderController(FolderService folderService) {
    //     this.folderService = folderService;
    // }

    // Endpoint to get all folders for a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getFoldersByUser(@PathVariable Long userId) {
        System.out.println(userId);
        return ResponseEntity.ok("not implemented");
    }

    // Endpoint to add folder for a specific user
    @PostMapping("/add")
    public ResponseEntity<?> getFoldersByUser(@RequestBody FolderRequest folderRequest) {
        System.out.println(folderRequest.getName());
        System.out.println(folderRequest.getSubjects());
        return ResponseEntity.ok("not implemented");
    }

    // Endpoint to get all emails in a folder with filters
    @GetMapping("/{folderId}/emails")
    public ResponseEntity<?> getEmailsInFolder(
            @PathVariable Long folderId,
            @RequestParam Long userId,
            @RequestParam(required = false) String subject
    ) {
        return ResponseEntity.ok("not implemented");
    }
}