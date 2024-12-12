package com.example.backend.DTO;

import com.example.backend.Entities.folder_by_subject;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class folder_by_subjectDTO {
    private long folder_id;
    private String filter_subject;
    public folder_by_subject toEntity(){
        return folder_by_subject.builder()
                .folder_id(folder_id)
                .filter_subject(filter_subject)
                .build();
    }
}
