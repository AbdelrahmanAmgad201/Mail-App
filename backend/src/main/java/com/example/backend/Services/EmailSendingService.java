package com.example.backend.Services;

import com.example.backend.Entities.*;
import com.example.backend.Repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmailSendingService {

    private final UserRepository userRepository;
    private final EmailRepository emailRepository;
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

        // Establish bidirectional relationship
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
}