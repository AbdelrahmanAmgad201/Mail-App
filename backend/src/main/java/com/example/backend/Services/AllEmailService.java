package com.example.backend.Services;

import com.example.backend.DTO.ReceiverDTO;
import com.example.backend.Entities.*;
import com.example.backend.Repositories.*;
import com.example.backend.DTO.ReceiverDTO.EmailDTO;
import com.example.backend.DTO.ReceiverDTO.EmailMetadataDTO;
import com.example.backend.DTO.ReceiverDTO.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AllEmailService {

    private final UserRepository userRepository;
    private final ReceiverRepository receiverRepository;
    private final StarredRepository starredRepository;

    @Transactional(readOnly = true)
    public List<ReceiverDTO> getAllEmails(Long userId) {
        // Find the user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Retrieve sent emails
        List<Email> sentEmails = receiverRepository.findEmailsBySender(user);
        List<ReceiverDTO> sentEmailDTOs = sentEmails.stream()
                .map(email -> createSentEmailDTO(email, user))
                .collect(Collectors.toList());

        // Retrieve received emails
        List<Receiver> receivedReceivers = receiverRepository.findByReceiver(user);
        List<ReceiverDTO> receivedEmailDTOs = receivedReceivers.stream()
                .map(receiver -> createReceivedEmailDTO(receiver, user))
                .collect(Collectors.toList());

        // Combine and sort emails by sent date
        List<ReceiverDTO> allEmails = new ArrayList<>(sentEmailDTOs);
        allEmails.addAll(receivedEmailDTOs);

        // Sort by date sent (most recent first)
        return allEmails.stream()
                .sorted(Comparator.comparing(dto ->
                                dto.getEmail().getMetadata().getDateSent(),
                        Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    private List<ReceiverDTO.AttachmentDTO> mapToAttachmentDTOs(Set<Attachment> attachments) {
        return attachments.stream()
                .map(attachment -> ReceiverDTO.AttachmentDTO.builder()
                        .attachmentId(attachment.getAttachmentId())
                        .fileName(attachment.getFileName())
                        .fileType(attachment.getFileType())
                        .fileSize(attachment.getFileSize())
                        .build())
                .collect(Collectors.toList());
    }

    private ReceiverDTO createSentEmailDTO(Email email, User currentUser) {
        List<Receiver> allReceivers = receiverRepository.findByEmail(email);
        boolean isStarred = starredRepository.findByUserAndEmail(currentUser, email)
                .isPresent();

        return ReceiverDTO.builder()
                .email(EmailDTO.builder()
                        .emailId(email.getEmailId())
                        .subject(email.getSubject())
                        .body(email.getBody())
                        .sender(mapToUserDTO(email.getSender()))
                        .metadata(mapToEmailMetadataDTO(email.getMetadata()))
                        .attachments(mapToAttachmentDTOs(email.getAttachments()))  // Add this line
                        .build())
                .receivers(allReceivers.stream()
                        .map(r -> mapToUserDTO(r.getReceiver()))
                        .collect(Collectors.toList()))
                .isStarred(isStarred)
                .build();
    }

    private ReceiverDTO createReceivedEmailDTO(Receiver receiver, User currentUser) {
        List<Receiver> allReceivers = receiverRepository.findByEmail(receiver.getEmail());
        boolean isStarred = starredRepository.findByUserAndEmail(currentUser, receiver.getEmail())
                .isPresent();

        return ReceiverDTO.builder()
                .id(receiver.getId())
                .email(EmailDTO.builder()
                        .emailId(receiver.getEmail().getEmailId())
                        .subject(receiver.getEmail().getSubject())
                        .body(receiver.getEmail().getBody())
                        .sender(mapToUserDTO(receiver.getEmail().getSender()))
                        .metadata(mapToEmailMetadataDTO(receiver.getEmail().getMetadata()))
                        .attachments(mapToAttachmentDTOs(receiver.getEmail().getAttachments()))  // Add this line
                        .build())
                .receivers(allReceivers.stream()
                        .map(r -> mapToUserDTO(r.getReceiver()))
                        .collect(Collectors.toList()))
                .isRead(receiver.getIsRead())
                .isTrashed(receiver.getIsTrashed())
                .isStarred(isStarred)
                .dateTrashed(receiver.getDateTrashed())
                .build();
    }

    // Utility mapping methods (same as in previous services)
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