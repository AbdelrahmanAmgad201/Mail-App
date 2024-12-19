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
public class DraftUpdateDTO {
    private Long draftId;
    private String subject;
    private String body;
    private List<String> receiverEmails;
    private String priority;
    private List<String> fileNames;
    private List<String> fileTypes;
    private List<String> fileSizes;
    private List<byte[]> content;
    private Boolean keepAsDraft;
}