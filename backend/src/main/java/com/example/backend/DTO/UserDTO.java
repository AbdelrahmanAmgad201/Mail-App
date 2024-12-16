package com.example.backend.DTO;

import com.example.backend.Entities.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO  {
    private long user_id;
    private String email_address;
    private String password_hash;
    private String first_name;
    private String last_name;

    public User toEntity(){
        return User.builder().user_id(user_id)
                .email_address(email_address)
                .password_hash(password_hash)
                .first_name(first_name)
                .last_name(last_name)
                .build();
    }
}
