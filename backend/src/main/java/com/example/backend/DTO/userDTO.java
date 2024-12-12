package com.example.backend.DTO;

import lombok.Builder;
import lombok.Data;
import com.example.backend.Entities.user;
@Data
@Builder
public class userDTO  {
    private long user_id;
    private String email_address;
    private String password_hash;
    private String first_name;
    private String last_name;

    public user toEntity(){
        return user.builder().user_id(user_id)
                .email_address(email_address)
                .password_hash(password_hash)
                .first_name(first_name)
                .last_name(last_name)
                .build();
    }
}
