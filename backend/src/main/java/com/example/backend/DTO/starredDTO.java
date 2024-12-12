package com.example.backend.DTO;

import lombok.Builder;
import lombok.Data;
import com.example.backend.Entities.starred;

@Builder
@Data
public class starredDTO {
    private long user_id;
    private String email_id;
    public starred toEntity(){
        return starred.builder()
                .email_id(email_id)
                .user_id(user_id)
                .build();
    }
}
