package com.example.backend.Entities.DTO;

import com.example.backend.Entities.receivers;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class receiversDTO {
    private long email_id;
    private long receiver_id;
    private boolean is_read;
    public receivers toEntity(){
        return receivers.builder()
                .email_id(email_id)
                .is_read(is_read)
                .receiver_id(receiver_id)
                .build();
    }
}
