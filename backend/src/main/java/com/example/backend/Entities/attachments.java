package com.example.backend.Entities;

import jakarta.persistence.Entity;

@Entity
public class attachments {
    private long email_id;
    private long attachment_id;
    private String file_name;
    private String file_type;
    private long file_size;
}
