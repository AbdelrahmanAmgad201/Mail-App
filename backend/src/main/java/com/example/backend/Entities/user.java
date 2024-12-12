package com.example.backend.Entities;

import jakarta.persistence.Entity;

@Entity
public class user {
    private long user_id;
    private String email_address;
    private String password_hash;
    private String first_name;
    private String last_name;

}
