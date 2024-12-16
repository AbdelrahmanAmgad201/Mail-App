package com.example.backend.Entities;

import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "emails")
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emailId;

    @Column(length = 255)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String body;

    @OneToOne
    @JoinColumn(name = "metadata_id")
    private EmailMetadata metadata;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @OneToMany(mappedBy = "email", cascade = CascadeType.ALL)
    private Set<Receiver> receivers;

    @OneToMany(mappedBy = "email", cascade = CascadeType.ALL)
    private Set<Attachment> attachments;

    public Long getEmailId() {
        return emailId;
    }

    public void setEmailId(Long emailId) {
        this.emailId = emailId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public EmailMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(EmailMetadata metadata) {
        this.metadata = metadata;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Set<Receiver> getReceivers() {
        return receivers;
    }

    public void setReceivers(Set<Receiver> receivers) {
        this.receivers = receivers;
    }

    public Set<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(Set<Attachment> attachments) {
        this.attachments = attachments;
    }

    public Email(Long emailId, String subject, String body, EmailMetadata metadata, User sender, Set<Receiver> receivers, Set<Attachment> attachments) {
        this.emailId = emailId;
        this.subject = subject;
        this.body = body;
        this.metadata = metadata;
        this.sender = sender;
        this.receivers = receivers;
        this.attachments = attachments;
    }

    public Email() {

    }
}