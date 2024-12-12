package com.example.backend.Entities;

import com.example.backend.Entities.DTO.emailsDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Builder
public class emails {
    @Id
    private long email_id;
    private String subject;
    private String body;
    private Date date_sent;
    private long sender_id;
    private Priority priority;
    private boolean is_spam;

    public emailsDTO toDTO(){
        return emailsDTO.builder().email_id(email_id)
                .date_sent(date_sent)
                .body(body)
                .date_sent(date_sent)
                .sender_id(sender_id)
                .priority(priority)
                .is_spam(is_spam).build();
    }

}
