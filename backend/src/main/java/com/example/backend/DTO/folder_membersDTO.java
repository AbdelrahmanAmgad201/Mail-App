package com.example.backend.DTO;

import com.example.backend.Entities.FolderMember;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class folder_membersDTO {
    private long folder_id;
    private long user_id;

    public FolderMember toEntity(){
        return FolderMember.builder()
                .folder_id(folder_id)
                .user_id(user_id)
                .build();
    }

}
