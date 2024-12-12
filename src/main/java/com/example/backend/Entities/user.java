package com.example.backend.Entities;
import  com.example.backend.Entities.DTO.userDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;

@Entity
@Builder
public class user {
    @Id
    private long user_id;
    private String email_address;
    private String password_hash;
    private String first_name;
    private String last_name;
    public userDTO toDTO(){
        return userDTO.builder()
                .user_id(user_id)
                .email_address(email_address)
                .password_hash(password_hash)
                .first_name(first_name)
                .last_name(last_name)
                .build();
    }

}
