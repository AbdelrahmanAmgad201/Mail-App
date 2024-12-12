package com.example.backend.Entities;

import com.example.backend.Entities.DTO.folder_by_subjectDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class folder_by_subject {
    @Id
    private long folder_id;
    private String filter_subject;
    public folder_by_subjectDTO toDTO() {
        return folder_by_subjectDTO.builder()
                .folder_id(folder_id)
                .filter_subject(filter_subject)
                .build();
    }
}
