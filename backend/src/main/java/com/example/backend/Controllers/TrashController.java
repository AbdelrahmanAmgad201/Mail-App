package com.example.backend.Controllers;

import com.example.backend.Services.ResponseDto;
import com.example.backend.Services.TrashService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/emails")
@RequiredArgsConstructor
public class TrashController {
    private final TrashService trashService;

    @PutMapping("/trash/{userId}/{emailId}")
    public ResponseEntity<ResponseDto> trashEmail(
            @PathVariable Long userId,
            @PathVariable Long emailId,
            @RequestParam(defaultValue = "false") boolean isSender
    ) {
        ResponseDto response = trashService.trashEmail(userId, emailId, isSender);
        return ResponseEntity.ok(response);
    }
}

