package com.example.backend.Entities.DTO;

import com.example.backend.Entities.contact_members;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class contact_membersDTO {
    private long contact_id;
    private long member_id;
    public contact_members toEntity(){
        return contact_members.builder()
                .contact_id(contact_id)
                .member_id(member_id)
                .build();
    }
}
