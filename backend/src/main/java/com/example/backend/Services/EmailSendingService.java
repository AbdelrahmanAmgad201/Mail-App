package com.example.backend.Services;

import com.example.backend.DTO.DraftUpdateDTO;
import com.example.backend.DTO.SendEmailRequestDTO;
import com.example.backend.Entities.*;
import com.example.backend.Repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
@Service
@RequiredArgsConstructor
public class EmailSendingService {

    private final UserRepository userRepository;
    private final EmailRepository emailRepository;
    private final AttachmentRepository attachmentRepository;
    private final EmailMetadataRepository emailMetadataRepository;
    private final ReceiverRepository receiverRepository;

    public Email sendEmail(SendEmailRequestDTO emailRequest) {
        // Validate input
        if (emailRequest.getReceiverEmails() == null || emailRequest.getReceiverEmails().isEmpty()) {
            throw new IllegalArgumentException("At least one receiver email is required");
        }

        // Find sender
        User sender = userRepository.findById(emailRequest.getSenderId())
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        // Validate priority
        Priority priority;
        try {
            priority = Priority.valueOf(emailRequest.getPriority().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid priority: " + emailRequest.getPriority());
        }

        // Create Email Metadata
        EmailMetadata metadata = EmailMetadata.builder()
                .dateSent(LocalDateTime.now())
                .priority(priority)
                .isTrashed(false)
                .isSpam(false)
                .isDraft(Boolean.TRUE.equals(emailRequest.getIsDraft()))
                .build();
        metadata = emailMetadataRepository.save(metadata);

        // Create Email
        Email email = Email.builder()
                .subject(emailRequest.getSubject())
                .body(emailRequest.getBody())
                .sender(sender)
                .metadata(metadata)
                .build();
        email = emailRepository.save(email);

        // Find or Create Receivers
        Set<Receiver> receivers = new HashSet<>();
        for (String receiverEmail : emailRequest.getReceiverEmails()) {
            // Validate email address
            if (!isValidEmailAddress(receiverEmail)) {
                throw new IllegalArgumentException("Invalid email address: " + receiverEmail);
            }

            // Find or create user
            User receiver = userRepository.findByEmailAddress(receiverEmail)
                    .orElseGet(() -> createNewUser(receiverEmail));

            // Create Receiver
            Receiver receiverEntity = Receiver.builder()
                    .email(email)
                    .receiver(receiver)
                    .isRead(false)
                    .isTrashed(false)
                    .build();

            receivers.add(receiverRepository.save(receiverEntity));
        }

        // Set receivers to email
        email.setReceivers(receivers);

        Set<Attachment> attachments = new HashSet<>();
        Path directoryPath = Paths.get("./backend/attachments");
        try {
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
                System.out.println("Directory created: " + directoryPath.toAbsolutePath());
            } else {
                System.out.println("Directory already exists: " + directoryPath.toAbsolutePath());
            }
        } catch (Exception e) {
            System.err.println("Failed to create attachments directory: " + e.getMessage());
            e.printStackTrace();

        }


        List<String> fileNames = emailRequest.getFileNames();
        List<String> fileTypes = emailRequest.getFileTypes();
        List<String> fileSizes = emailRequest.getFileSizes();
        List<byte[]> contents = emailRequest.getContent();

        for (int i = 0; i < fileNames.size(); i++) {

            try {

                String fileName = fileNames.get(i);
                String fileType = fileTypes.get(i);
                Long fileSize = Long.parseLong(fileSizes.get(i));
                byte[] content = contents.get(i);
                StringBuilder hexString = new StringBuilder();
                for (byte b : content) {
                    hexString.append(String.format("%02X ", b));
                }
                System.out.println("File content in hex: " + hexString.toString());

                System.out.println("Processing Attachment:");
                System.out.println("FileName: " + fileName);
                System.out.println("FileType: " + fileType);
                System.out.println("FileSize: " + fileSize);
                Path filePath = directoryPath.resolve(fileName);
                Files.write(filePath, content);
                System.out.println("File saved at: " + filePath.toAbsolutePath());


                // Build the Attachment object
                Attachment attachment = Attachment.builder()
                        .fileName(fileName)
                        .fileType(fileType)
                        .fileSize(fileSize)
                        .email(email) // Ensure the email relationship is maintained
                        .build();

                // Save attachment to the database
                attachment = attachmentRepository.save(attachment);

                // Add to the email's attachments set
                attachments.add(attachment);

            } catch (Exception e) {
                System.err.println("Failed to save attachment to file system: " + e.getMessage());
                e.printStackTrace();
            }
        }
        email.setAttachments(attachments);

        metadata.setEmail(email);
        return emailRepository.save(email);
    }

    // Email validation method
    private boolean isValidEmailAddress(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }

    private User createNewUser(String emailAddress) {
        // Split email to get first and last name (basic approach)
        String[] parts = emailAddress.split("@")[0].split("\\.");
        String firstName = parts.length > 0 ? capitalize(parts[0]) : "";
        String lastName = parts.length > 1 ? capitalize(parts[1]) : "";

        User newUser = User.builder()
                .emailAddress(emailAddress)
                .firstName(firstName)
                .lastName(lastName)
                .build();

        return userRepository.save(newUser);
    }

    // Helper method to capitalize first letter
    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    public Email updateDraft(DraftUpdateDTO updateRequest) {
        // Find the draft email
        Email draft = emailRepository.findById(updateRequest.getDraftId())
                .orElseThrow(() -> new RuntimeException("Draft not found"));

        // Verify it's actually a draft
        if (!Boolean.TRUE.equals(draft.getMetadata().getIsDraft())) {
            throw new IllegalStateException("Email is not a draft");
        }

        // Update basic fields if provided
        if (updateRequest.getSubject() != null) {
            draft.setSubject(updateRequest.getSubject());
        }
        if (updateRequest.getBody() != null) {
            draft.setBody(updateRequest.getBody());
        }

        // Update priority if provided
        if (updateRequest.getPriority() != null) {
            Priority priority;
            try {
                priority = Priority.valueOf(updateRequest.getPriority().toUpperCase());
                draft.getMetadata().setPriority(priority);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid priority: " + updateRequest.getPriority());
            }
        }

        // Handle receivers update if provided
        if (updateRequest.getReceiverEmails() != null) {
            // Remove old receivers
            receiverRepository.deleteAll(draft.getReceivers());
            draft.getReceivers().clear();

            // Add new receivers
            Set<Receiver> newReceivers = new HashSet<>();
            for (String receiverEmail : updateRequest.getReceiverEmails()) {
                if (!isValidEmailAddress(receiverEmail)) {
                    throw new IllegalArgumentException("Invalid email address: " + receiverEmail);
                }

                User receiver = userRepository.findByEmailAddress(receiverEmail)
                        .orElseGet(() -> createNewUser(receiverEmail));

                Receiver receiverEntity = Receiver.builder()
                        .email(draft)
                        .receiver(receiver)
                        .isRead(false)
                        .isTrashed(false)
                        .build();

                newReceivers.add(receiverRepository.save(receiverEntity));
            }
            draft.setReceivers(newReceivers);
        }

        // Handle attachments update if provided
        if (updateRequest.getFileNames() != null && !updateRequest.getFileNames().isEmpty()) {
            // Remove old attachments
            for (Attachment oldAttachment : draft.getAttachments()) {
                try {
                    // Delete physical file
                    Path filePath = Paths.get("./backend/attachments/", oldAttachment.getFileName());
                    Files.deleteIfExists(filePath);
                } catch (IOException e) {
                    System.err.println("Failed to delete old attachment file: " + e.getMessage());
                }
            }
            attachmentRepository.deleteAll(draft.getAttachments());
            draft.getAttachments().clear();

            // Add new attachments
            Set<Attachment> newAttachments = new HashSet<>();
            for (int i = 0; i < updateRequest.getFileNames().size(); i++) {
                try {
                    String fileName = updateRequest.getFileNames().get(i);
                    String fileType = updateRequest.getFileTypes().get(i);
                    Long fileSize = Long.parseLong(updateRequest.getFileSizes().get(i));
                    byte[] content = updateRequest.getContent().get(i);

                    // Save file
                    Path directoryPath = Paths.get("./backend/attachments");
                    Path filePath = directoryPath.resolve(fileName);
                    Files.write(filePath, content);

                    // Create attachment
                    Attachment attachment = Attachment.builder()
                            .fileName(fileName)
                            .fileType(fileType)
                            .fileSize(fileSize)
                            .email(draft)
                            .build();

                    newAttachments.add(attachmentRepository.save(attachment));
                } catch (Exception e) {
                    throw new RuntimeException("Failed to save attachment: " + e.getMessage());
                }
            }
            draft.setAttachments(newAttachments);
        }

        // Update draft status
        if (!Boolean.TRUE.equals(updateRequest.getKeepAsDraft())) {
            draft.getMetadata().setIsDraft(false);
            draft.getMetadata().setDateSent(LocalDateTime.now());
        }

        return emailRepository.save(draft);
    }
}