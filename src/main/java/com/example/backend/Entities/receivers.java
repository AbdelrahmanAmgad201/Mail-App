package com.example.backend.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import com.example.backend.Entities.DTO.receiversDTO;
@Entity
@Builder
public class receivers {
    private long email_id;
    @Id
    private long receiver_id;
    private boolean is_read;
    public receiversDTO toDTO(){
        return  receiversDTO.builder()
                .email_id(email_id)
                .receiver_id(receiver_id)
                .is_read(is_read)
                .build();
    }

}
