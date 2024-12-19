package com.example.backend.Controllers;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;
@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class AttachmentsController {

    private static final String ATTACHMENTS_DIR = "D:\\Faculty of Engineering, Alexandria University\\CSED 2nd year\\1st Term\\Programming 2\\mail\\Mail-Server\\backend\\backend\\attachments";

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam String fileName) {
        try {
            // Use the absolute path to the attachments folder
            Path filePath = Paths.get(ATTACHMENTS_DIR).resolve(fileName).normalize();

            // Load file as a resource
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                // Set headers to trigger a download
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
