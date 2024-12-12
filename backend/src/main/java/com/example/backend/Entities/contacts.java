package com.example.backend.Entities;

import jakarta.persistence.Entity;

@Entity
public class contacts {
    private long owner_id;
    private long contact_id;
    private String contact_name;
}
