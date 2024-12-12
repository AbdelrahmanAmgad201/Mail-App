package com.example.backend.Entities;

import com.example.backend.Entities.DTO.attachmentsDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class attachments {

    private long email_id;
    @Id
    private long attachment_id;
    private String file_name;
    private String file_type;
    private long file_size;
    public attachmentsDTO toDTO(){
        return attachmentsDTO.builder()
                .attachment_id(attachment_id)
                .email_id(email_id)
                .file_name(file_name)
                .file_type(file_type)
                .file_size(file_size)
                .build();
    }
}
