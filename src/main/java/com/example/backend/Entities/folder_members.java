package com.example.backend.Entities;

import com.example.backend.Entities.DTO.folder_membersDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class folder_members {
    @Id
    private long folder_id;
    private long user_id;

    public folder_membersDTO toDTO(){
        return folder_membersDTO.builder()
                .folder_id(folder_id)
                .user_id(user_id)
                .build();
    }
}
