package com.example.backend.Services;

import com.example.backend.Entities.*;
import com.example.backend.Repositories.*;
import com.example.backend.Services.ReceiverDTO.EmailDTO;
import com.example.backend.Services.ReceiverDTO.EmailMetadataDTO;
import com.example.backend.Services.ReceiverDTO.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SentEmailService {

    private final UserRepository userRepository;
    private final ReceiverRepository receiverRepository;
    private final StarredRepository starredRepository;

    @Transactional(readOnly = true)
    public List<ReceiverDTO> getSentEmails(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Find emails sent by this user
        List<Email> sentEmails = receiverRepository.findEmailsBySender(user);

        return sentEmails.stream()
                .map(email -> mapToSentEmailDTO(email, user))
                .collect(Collectors.toList());
    }

    private ReceiverDTO mapToSentEmailDTO(Email email, User currentUser) {
        // Find all receivers for this email
        List<Receiver> allReceivers = receiverRepository.findByEmail(email);

        // Check if the email is starred
        boolean isStarred = starredRepository.findByUserAndEmail(currentUser, email)
                .isPresent();

        return ReceiverDTO.builder()
                .email(EmailDTO.builder()
                        .emailId(email.getEmailId())
                        .subject(email.getSubject())
                        .body(email.getBody())
                        .sender(mapToUserDTO(email.getSender()))
                        .metadata(mapToEmailMetadataDTO(email.getMetadata()))
                        .build())
                .receivers(allReceivers.stream()
                        .map(r -> mapToUserDTO(r.getReceiver()))
                        .collect(Collectors.toList()))
                .isStarred(isStarred)
                .build();
    }

    // Utility methods for mapping
    private UserDTO mapToUserDTO(User user) {
        return UserDTO.builder()
                .userId(user.getUserId())
                .emailAddress(user.getEmailAddress())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    private EmailMetadataDTO mapToEmailMetadataDTO(EmailMetadata metadata) {
        return EmailMetadataDTO.builder()
                .metadataId(metadata.getMetadataId())
                .isTrashed(metadata.getIsTrashed())
                .dateTrashed(metadata.getDateTrashed())
                .priority(metadata.getPriority())
                .isSpam(metadata.getIsSpam())
                .dateSent(metadata.getDateSent())
                .build();
    }
}