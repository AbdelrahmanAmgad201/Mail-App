package com.example.backend.Entities;

import jakarta.persistence.Entity;

import java.util.Date;

@Entity
public class emails {
    private long email_id;
    private String subject;
    private String body;
    private Date date_sent;
    private long sender_id;
    private Priority priority;
    private boolean is_spam;

}
