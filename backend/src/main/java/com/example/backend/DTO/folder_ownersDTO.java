package com.example.backend.DTO;

import com.example.backend.Entities.FolderOwner;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class folder_ownersDTO {
    private long folder_id;
    private String folder_name;
    private  String user_id;

    public FolderOwner toEntity(){
        return FolderOwner.builder()
                .folder_id(folder_id)
                .folder_name(folder_name)
                .user_id(user_id)
                .build();
    }
}
