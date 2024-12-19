package com.example.backend.Controllers;

import com.example.backend.Entities.FolderOwner;
import com.example.backend.Services.FolderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/folders")
public class FolderController {

    private final FolderService folderService;

    public FolderController(FolderService folderService) {
        this.folderService = folderService;
    }

    @PostMapping("/create-with-filters")
    public ResponseEntity<?> createFolderWithFilters(@RequestParam Long userId,
                                                     @RequestParam String folderName,
                                                     @RequestParam List<String> subjectFilters,
                                                     @RequestParam List<Long> memberIds) {
        try {
            FolderOwner folder = folderService.createFolderWithFilters(userId, folderName, subjectFilters, memberIds);
            return ResponseEntity.ok(folder);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
