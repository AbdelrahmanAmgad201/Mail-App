//package com.example.backend.Controllers;
//
//import com.example.backend.Entities.Email;
//import com.example.backend.Services.StarredService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/starred")
//public class StarredController {
//
//    private final StarredService starredService;
//
//    public StarredController(StarredService starredService) {
//        this.starredService = starredService;
//    }
//
//    // Endpoint to get Starred email IDs by user ID
//    @GetMapping("/{userId}/email-ids")
//    public ResponseEntity<List<Long>> getStarredEmailIdsByUserId(@PathVariable Long userId) {
//        List<Long> emailIds = starredService.getStarredEmailIdsByUserId(userId);
//        return ResponseEntity.ok(emailIds);
//    }
//
//    // Endpoint to get Starred emails by user ID
//    @GetMapping("/{userId}/emails")
//    public ResponseEntity<List<Email>> getStarredEmailsByUserId(@PathVariable Long userId) {
//        List<Email> emails = starredService.getStarredEmailsByUserId(userId);
//        return ResponseEntity.ok(emails);
//    }
//}
