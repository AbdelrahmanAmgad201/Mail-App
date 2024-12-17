package com.example.backend.Services;

import com.example.backend.Entities.Attachment;
import com.example.backend.Repositories.AttachmentRepository;
import com.example.backend.Repositories.EmailRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final EmailRepository emailRepository;

    public AttachmentService(AttachmentRepository attachmentRepository, EmailRepository emailRepository) {
        this.attachmentRepository = attachmentRepository;
        this.emailRepository = emailRepository;
    }

    // Save an attachment to an email
    public Long saveAttachment(Long emailId, String fileName, String fileType, Long fileSize) {
        // Find the email by ID
        Email email = emailRepository.findById(emailId);

        // Create a new attachment object
        Attachment attachment = Attachment.builder()
                .fileName(fileName)
                .fileType(fileType)
                .fileSize(fileSize)
                .email(email)
                .build();


        attachmentRepository.save(attachment);
        return  attachment.getID();
    }


    public List<Attachment> getAttachmentsByEmail(Long emailId) {
        // Find the email by ID
        emailRepository.findById(emailId);

        // Return all attachments for this email
        return attachmentRepository.findByEmail_EmailId(emailId);
    }

    // Delete an attachment by its ID
    public void deleteAttachment(Long attachmentId) {
        // Check if the attachment exists
        Optional<Attachment> attachment = attachmentRepository.findById(attachmentId);
        if (attachment.isPresent()) {
            attachmentRepository.delete(attachment.get());
        } else {
            throw new RuntimeException("Attachment not found");
        }
    }
}
