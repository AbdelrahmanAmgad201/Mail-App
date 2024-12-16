package com.example.backend.Entities;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.Date;

@Entity
@Table(name = "receivers")

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
    private Boolean isTrashed;
    private Date dateTrashed;

    public Receiver(Long id, Email email, User receiver, Boolean isRead, Boolean isTrashed, Date dateTrashed) {
        this.id = id;
        this.email = email;
        this.receiver = receiver;
        this.isRead = isRead;
        this.isTrashed = isTrashed;
        this.dateTrashed = dateTrashed;
    }

    public Boolean getTrashed() {
        return isTrashed;
    }

    public void setTrashed(Boolean trashed) {
        isTrashed = trashed;
    }

    public Date getDateTrashed() {
        return dateTrashed;
    }

    public void setDateTrashed(Date dateTrashed) {
        this.dateTrashed = dateTrashed;
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

}