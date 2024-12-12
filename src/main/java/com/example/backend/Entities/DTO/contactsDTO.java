package com.example.backend.Entities.DTO;

import com.example.backend.Entities.contacts;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class contactsDTO {
    private long owner_id;

    private long contact_id;
    private String contact_name;

    public contacts toEntity(){
        return contacts.builder()
                .owner_id(owner_id)
                .contact_id(contact_id)
                .contact_name(contact_name)
                .build();
    }
}
