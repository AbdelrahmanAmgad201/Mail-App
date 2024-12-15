package com.example.backend.Entities;

import com.example.backend.DTO.ReceiverDTO;
import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table(name = "receivers")
@Builder
public class Receiver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "email_id", nullable = false)
    private Email email;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;

    private Boolean isRead;

    public Receiver(Boolean isRead, User receiver, Email email, Long id) {
        this.isRead = isRead;
        this.receiver = receiver;
        this.email = email;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

//    public ReceiverDTO toDTO() {
//        return ReceiverDTO.builder()
//                .emailId(email.getEmailId())
//                .receiverId(receiver.getUserId())
//                .isRead(isRead)
//                .build();
//    }

}