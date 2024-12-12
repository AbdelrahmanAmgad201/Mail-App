package com.example.backend.Entities.DTO;

import com.example.backend.Entities.folder_owners;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class folder_ownersDTO {
    private long folder_id;
    private String folder_name;
    private  String user_id;

    public folder_owners toEntity(){
        return folder_owners.builder()
                .folder_id(folder_id)
                .folder_name(folder_name)
                .user_id(user_id)
                .build();
    }
}
