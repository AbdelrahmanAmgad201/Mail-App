package com.example.backend.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import com.example.backend.Entities.DTO.starredDTO;
@Entity
@Builder
public class starred {
    @Id
    private long user_id;
    private String email_id;
    public starredDTO toDTO(){
        return starredDTO.builder()
                .email_id(email_id)
                .user_id(user_id)
                .build();
    }
}
