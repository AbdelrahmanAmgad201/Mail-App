package com.example.backend.DTO;

import lombok.Builder;
import lombok.Data;
import com.example.backend.Entities.Starred;

@Builder
@Data
public class StarredDTO {
    private long user_id;
    private String email_id;
    public Starred toEntity(){
        return Starred.builder()
                .email_id(email_id)
                .user_id(user_id)
                .build();
    }
}
