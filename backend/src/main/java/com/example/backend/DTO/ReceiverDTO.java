package com.example.backend.DTO;

import com.example.backend.Entities.Receiver;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ReceiverDTO {
    private long email_id;
    private long receiver_id;
    private boolean is_read;
    public Receiver toEntity(){
        return Receiver.builder()
                .email_id(email_id)
                .is_read(is_read)
                .receiver_id(receiver_id)
                .build();
    }
}
