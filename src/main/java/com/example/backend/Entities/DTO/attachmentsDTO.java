package com.example.backend.Entities.DTO;

import com.example.backend.Entities.attachments;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class attachmentsDTO {
    private long email_id;

    private long attachment_id;
    private String file_name;
    private String file_type;
    private long file_size;

    public attachments toEntity() {
        return attachments.builder()
                .email_id(email_id)
                .attachment_id(attachment_id)
                .file_name(file_name)
                .file_type(file_type)
                .file_size(file_size)
                .build();
    }
}
