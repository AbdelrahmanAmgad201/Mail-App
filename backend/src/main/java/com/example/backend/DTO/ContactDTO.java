package com.example.backend.DTO;

import com.example.backend.Entities.Contact;
import com.example.backend.Entities.ContactMember;
import com.example.backend.Entities.User;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Builder
@Data
public class ContactDTO {

    private Long contactId;
    private User owner;

    private String contactName;

    private Set<ContactMember> members;

    public Contact toEntity(User owner) {
        return Contact.builder()
                .contactId(contactId)
                .contactName(contactName)
                .owner(owner)
                .build();
    }
}
