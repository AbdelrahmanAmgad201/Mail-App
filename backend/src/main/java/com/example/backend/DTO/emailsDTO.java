package com.example.backend.DTO;

import com.example.backend.Entities.Priority;
import com.example.backend.Entities.emails;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class emailsDTO {
    private long email_id;
    private String subject;
    private String body;
    private Date date_sent;
    private long sender_id;
    private Priority priority;
    private boolean is_spam;
    public emails toEntity(){
        return emails.builder()
                .email_id(email_id)
                .body(body)
                .subject(subject)
                .date_sent(date_sent)
                .sender_id(sender_id)
                .priority(priority)
                .is_spam(is_spam)
                .build();
    }
}
