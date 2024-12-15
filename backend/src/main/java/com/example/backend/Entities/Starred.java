package com.example.backend.Entities;

import com.example.backend.DTO.StarredDTO;
import jakarta.persistence.*;
import lombok.Builder;


@Entity
@Table(name = "starred")
@Builder
public class Starred {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "email_id", nullable = false)
    private Email email;

    public Starred(Long id, User user, Email email) {
        this.id = id;
        this.user = user;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

//    public StarredDTO toDTO() {
//        return StarredDTO.builder()
//                .userId(user.getUserId())
//                .emailId(email.getEmailId())
//                .build();
//    }

}