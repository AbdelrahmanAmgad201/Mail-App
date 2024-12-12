package com.example.backend.Entities.DTO;

import com.example.backend.Entities.folder_members;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class folder_membersDTO {
    private long folder_id;
    private long user_id;

    public folder_members toEntity(){
        return folder_members.builder()
                .folder_id(folder_id)
                .user_id(user_id)
                .build();
    }

}
