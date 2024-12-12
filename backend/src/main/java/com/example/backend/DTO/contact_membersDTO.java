package com.example.backend.DTO;

import com.example.backend.Entities.ContactMember;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class contact_membersDTO {
    private long contact_id;
    private long member_id;
    public ContactMember toEntity(){
        return ContactMember.builder()
                .contact_id(contact_id)
                .member_id(member_id)
                .build();
    }
}
