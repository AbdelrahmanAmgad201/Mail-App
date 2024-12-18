package com.example.backend.Services;

import com.example.backend.DTO.ReceiverDTO;
import com.example.backend.Entities.*;
import com.example.backend.Repositories.ReceiverRepository;
import com.example.backend.Repositories.UserRepository;
import com.example.backend.Repositories.StarredRepository;
import com.example.backend.DTO.ReceiverDTO.EmailDTO;
import com.example.backend.DTO.ReceiverDTO.EmailMetadataDTO;
import com.example.backend.DTO.ReceiverDTO.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReceiverService {

    private final ReceiverRepository receiverRepository;
    private final UserRepository userRepository;
    private final StarredRepository starredRepository;

    @Transactional(readOnly = true)
    public List<ReceiverDTO> getInboxEmails(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Receiver> receivers = receiverRepository.findByReceiver(user);

        return receivers.stream()
                .map(receiver -> mapToReceiverDTO(receiver, user))
                .collect(Collectors.toList());
    }

    private ReceiverDTO mapToReceiverDTO(Receiver receiver, User currentUser) {
        // Find all receivers for this email
        List<Receiver> allReceivers = receiverRepository.findByEmail(receiver.getEmail());

        // Check if the email is starred
        boolean isStarred = starredRepository.findByUserAndEmail(currentUser, receiver.getEmail()).isPresent();

        return ReceiverDTO.builder()
                .id(receiver.getId())
                .email(EmailDTO.builder()
                        .emailId(receiver.getEmail().getEmailId())
                        .subject(receiver.getEmail().getSubject())
                        .body(receiver.getEmail().getBody())
                        .sender(mapToUserDTO(receiver.getEmail().getSender()))
                        .metadata(mapToEmailMetadataDTO(receiver.getEmail().getMetadata()))
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

    // Existing mapping methods remain the same
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

//    @Transactional
//    public ReceiverDTO markEmailAsRead(Long receiverId) {
//        Receiver receiver = receiverRepository.findById(receiverId)
//                .orElseThrow(() -> new RuntimeException("Receiver not found"));
//
//        receiver.setIsRead(true);
//        Receiver updatedReceiver = receiverRepository.save(receiver);
//
//        return mapToReceiverDTO(updatedReceiver, updatedReceiver.getReceiver());
//    }
//
//    @Transactional
//    public ReceiverDTO moveEmailToTrash(Long receiverId) {
//        Receiver receiver = receiverRepository.findById(receiverId)
//                .orElseThrow(() -> new RuntimeException("Receiver not found"));
//
//        receiver.setIsTrashed(true);
//        receiver.setDateTrashed(new java.util.Date());
//        Receiver updatedReceiver = receiverRepository.save(receiver);
//
//        return mapToReceiverDTO(updatedReceiver, updatedReceiver.getReceiver());
//    }
}