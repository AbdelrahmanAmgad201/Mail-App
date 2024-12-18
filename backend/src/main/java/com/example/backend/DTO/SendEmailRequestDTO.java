package com.example.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendEmailRequestDTO {
    private Long senderId;
    private List<String> receiverEmails;
    private String subject;
    private String body;
    private String priority; // Can be LOW, MEDIUM, HIGH
}