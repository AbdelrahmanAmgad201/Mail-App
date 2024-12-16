package com.example.backend.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "attachments")

public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long attachmentId;

    private final String fileName;
    private final String fileType;
    private final Long fileSize;
    @ManyToOne
    @JoinColumn(name = "email_id", nullable = false)
    private final Email email;

    public Attachment(Long attachmentId, String fileName, String fileType, Long fileSize, Email email) {
        this.attachmentId = attachmentId;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.email = email;
    }

}