package com.example.backend.Entities;

import jakarta.persistence.Entity;

@Entity
public class receivers {
    private long email_id;
    private long receiver_id;
    private boolean is_read;

}
