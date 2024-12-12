package com.example.backend.Entities;

import com.example.backend.Entities.DTO.folder_ownersDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Entity
@Builder
@Data
public class folder_owners {
    @Id
    private long folder_id;
    private String folder_name;
    private  String user_id;
    public folder_ownersDTO toDTO(){
        return folder_ownersDTO.builder()
                .folder_id(folder_id)
                .folder_name(folder_name)
                .user_id(user_id).build();
    }


}
