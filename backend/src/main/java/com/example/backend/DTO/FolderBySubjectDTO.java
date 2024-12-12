package com.example.backend.DTO;

import com.example.backend.Entities.FolderBySubject;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FolderBySubjectDTO {
    private long folder_id;
    private String filter_subject;
    public FolderBySubject toEntity(){
        return FolderBySubject.builder()
                .folder_id(folder_id)
                .filter_subject(filter_subject)
                .build();
    }
}
