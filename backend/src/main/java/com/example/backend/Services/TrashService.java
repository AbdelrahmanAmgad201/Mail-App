package com.example.backend.Services;

import com.example.backend.Entities.Email;
import com.example.backend.Entities.EmailMetadata;
import com.example.backend.Entities.Receiver;
import com.example.backend.Entities.User;
import com.example.backend.Repositories.EmailRepository;
import com.example.backend.Repositories.ReceiverRepository;
import com.example.backend.Repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrashService {
    private final EmailRepository emailRepository;
    private final ReceiverRepository receiverRepository;
    private final UserRepository userRepository;

    @Transactional
    public ResponseDto trashEmail(Long userId, Long emailId, boolean isSender) {
        // Validate user exists
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // Find the email
        Email email = emailRepository.findById(emailId)
                .orElseThrow(() -> new EntityNotFoundException("Email not found"));

        // Handle trashing for sender
        if (isSender) {

            // Toggle sender's trash status
            EmailMetadata metadata = email.getMetadata();
            metadata.setIsTrashed(!Boolean.TRUE.equals(metadata.getIsTrashed()));
            metadata.setDateTrashed(metadata.getIsTrashed() ? new Date() : null);

            emailRepository.save(email);

            return new ResponseDto(
                    "Email " + (metadata.getIsTrashed() ? "trashed" : "restored") + " for sender",
                    metadata.getIsTrashed()
            );
        }

        // Handle trashing for receiver
        else {
            // Find the receiver entry for this user and email
            Optional<Receiver> receiverOptional = receiverRepository
                    .findByReceiverUserIdAndEmailId(userId, emailId);

            if (receiverOptional.isPresent()) {
                Receiver receiver = receiverOptional.get();
                // Toggle receiver's trash status
                receiver.setIsTrashed(!Boolean.TRUE.equals(receiver.getIsTrashed()));
                receiver.setDateTrashed(receiver.getIsTrashed() ? new Date() : null);

                receiverRepository.save(receiver);

                return new ResponseDto(
                        "Email " + (receiver.getIsTrashed() ? "trashed" : "restored") + " for receiver",
                        receiver.getIsTrashed()
                );
            } else {
                // If no receiver found, it might be the sender trashing their sent email
                EmailMetadata metadata = email.getMetadata();
                metadata.setIsTrashed(!Boolean.TRUE.equals(metadata.getIsTrashed()));
                metadata.setDateTrashed(metadata.getIsTrashed() ? new Date() : null);

                emailRepository.save(email);

                return new ResponseDto(
                        "Email " + (metadata.getIsTrashed() ? "trashed" : "restored") + " for sender",
                        metadata.getIsTrashed()
                );
            }
        }
    }
}