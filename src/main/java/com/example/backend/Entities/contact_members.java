package com.example.backend.Entities;

import com.example.backend.Entities.DTO.contact_membersDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class contact_members {
    @Id
    private long contact_id;
    private long member_id;
    public contact_membersDTO toDTO(){
        return contact_membersDTO.builder()
                .member_id(member_id)
                .contact_id(contact_id)
                .build();
    }
}
