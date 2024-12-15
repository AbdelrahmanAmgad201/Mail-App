package com.example.backend.DTO;

import com.example.backend.Entities.Contact;
import com.example.backend.Entities.ContactMember;
import com.example.backend.Entities.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactMemberDTO {
    private long contact_id;
    private long memberId;
    public ContactMember toEntity(Contact contact, User member) {
        return ContactMember.builder()
                .memberId(memberId)
                .contact(contact)
                .member(member)
                .build();
    }
}
