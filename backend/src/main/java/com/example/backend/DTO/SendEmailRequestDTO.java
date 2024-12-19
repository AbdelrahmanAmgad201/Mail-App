package com.example.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.backend.Entities.Attachment;

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
    private List<String> fileNames;
    private List<String> fileTypes;
    private List<String> fileSizes;
    private List<byte[]> content;
    private Boolean isDraft;
}