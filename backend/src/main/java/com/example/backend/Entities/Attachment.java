package com.example.backend.Entities;

import com.example.backend.DTO.AttachmentDTO;
import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table(name = "attachments")
@Builder
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attachmentId;

    private String fileName;
    private String fileType;
    private Long fileSize;

    public Attachment(Long attachmentId, String fileName, String fileType, Long fileSize, Email email) {
        this.attachmentId = attachmentId;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.email = email;
    }

    @ManyToOne
    @JoinColumn(name = "email_id", nullable = false)
    private Email email;

    public Long getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Long attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

//    public AttachmentDTO toDTO() {
//        return AttachmentDTO.builder()
//                .attachmentId(attachmentId)
//                .emailId(email.getEmailId())
//                .fileName(fileName)
//                .fileType(fileType)
//                .fileSize(fileSize)
//                .build();
//    }



}