package com.example.backend.DTO;
import com.example.backend.Entities.Priority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceiverDTO {
    private Long id;
    private EmailDTO email;
    private List<UserDTO> receivers;
    private Boolean isStarred;
    private Boolean isRead;
    private Boolean isTrashed;
    private Date dateTrashed;

    public ReceiverDTO(ReceiverDTO other) {
        this.id = other.id;
        this.email = other.email != null ? new EmailDTO(other.email) : null;
        this.receivers = other.receivers != null ? List.copyOf(other.receivers) : null; // Shallow copy of the list
        this.isStarred = other.isStarred;
        this.isRead = other.isRead;
        this.isTrashed = other.isTrashed;
        this.dateTrashed = other.dateTrashed != null ? new Date(other.dateTrashed.getTime()) : null; // Deep copy of Date
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EmailDTO {
        private Long emailId;
        private String subject;
        private String body;
        private UserDTO sender;
        private EmailMetadataDTO metadata;
        private List<AttachmentDTO> attachments;

        public EmailDTO(EmailDTO other) {
            this.emailId = other.emailId;
            this.subject = other.subject;
            this.body = other.body;
            this.sender = other.sender != null ? new UserDTO(other.sender) : null;
            this.metadata = other.metadata != null ? new EmailMetadataDTO(other.metadata) : null;
            this.attachments = other.attachments != null ? List.copyOf(other.attachments) : null; // Shallow copy of the list
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AttachmentDTO {
        private Long attachmentId;
        private String fileName;
        private String fileType;
        private Long fileSize;

        public AttachmentDTO(AttachmentDTO other) {
            this.attachmentId = other.attachmentId;
            this.fileName = other.fileName;
            this.fileType = other.fileType;
            this.fileSize = other.fileSize;
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserDTO {
        private Long userId;
        private String emailAddress;
        private String firstName;
        private String lastName;

        public UserDTO(UserDTO other) {
            this.userId = other.userId;
            this.emailAddress = other.emailAddress;
            this.firstName = other.firstName;
            this.lastName = other.lastName;
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EmailMetadataDTO {
        private Long metadataId;
        private Boolean isTrashed;
        private Date dateTrashed;
        private Priority priority;
        private Boolean isSpam;
        private LocalDateTime dateSent;

        public EmailMetadataDTO(EmailMetadataDTO other) {
            this.metadataId = other.metadataId;
            this.isTrashed = other.isTrashed;
            this.dateTrashed = other.dateTrashed != null ? new Date(other.dateTrashed.getTime()) : null; // Deep copy of Date
            this.priority = other.priority;
            this.isSpam = other.isSpam;
            this.dateSent = other.dateSent != null ? other.dateSent : null; // LocalDateTime is immutable
        }

    }
}