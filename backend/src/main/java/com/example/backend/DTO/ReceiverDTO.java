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
        private Boolean isDraft;
    }
}