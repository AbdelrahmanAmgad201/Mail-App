package com.example.backend.Entities;

import com.example.backend.Entities.DTO.contactsDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class contacts {

    private long owner_id;
    @Id
    private long contact_id;
    private String contact_name;

    public contactsDTO toDTO(){
        return contactsDTO.builder()
                .owner_id(owner_id)
                .contact_name(contact_name)
                .contact_id(contact_id).build();
    }
}
