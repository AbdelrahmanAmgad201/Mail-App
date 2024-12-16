//package com.example.backend.Controllers;
//
//import com.example.backend.Entities.Email;
//import com.example.backend.Entities.FolderOwner;
//import com.example.backend.Services.FolderService;
//import com.example.backend.Services.StarredService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/folders")
//public class FolderController {
//
//
//    private final FolderService folderService;
//
//    public FolderController(FolderService folderService) {
//        this.folderService = folderService;
//    }
//
//    // Endpoint to get all folders for a specific user
//    @GetMapping("/user/{userId}")
//    public List<FolderOwner> getFoldersByUser(@PathVariable Long userId) {
//        return folderService.getFoldersByUser(userId);
//    }
//
//    // Endpoint to get all emails in a folder with filters
//    @GetMapping("/{folderId}/emails")
//    public List<Email> getEmailsInFolder(
//            @PathVariable Long folderId,
//            @RequestParam Long userId,
//            @RequestParam(required = false) String subject
//    ) {
//        return folderService.getEmailsByFolderAndFilters(folderId, userId, subject);
//    }
//}