package com.example.backend.Entities;

import com.example.backend.DTO.EmailDTO;
import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "emails")
@Builder
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emailId;

    private String subject;
    private String body;
    private String priority;
    private Boolean isSpam;

    @Column(nullable = false)
    private java.time.LocalDateTime dateSent;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @OneToMany(mappedBy = "email")
    private Set<Receiver> receivers;

    @OneToMany(mappedBy = "email")
    private Set<Attachment> attachments;

    public Email(Long emailId, String subject, String body, String priority, Boolean isSpam, LocalDateTime dateSent, User sender, Set<Receiver> receivers, Set<Attachment> attachments) {
        this.emailId = emailId;
        this.subject = subject;
        this.body = body;
        this.priority = priority;
        this.isSpam = isSpam;
        this.dateSent = dateSent;
        this.sender = sender;
        this.receivers = receivers;
        this.attachments = attachments;
    }

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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Boolean getSpam() {
        return isSpam;
    }

    public void setSpam(Boolean spam) {
        isSpam = spam;
    }

    public LocalDateTime getDateSent() {
        return dateSent;
    }

    public void setDateSent(LocalDateTime dateSent) {
        this.dateSent = dateSent;
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
//
//    public EmailDTO toDTO() {
//        return EmailDTO.builder()
//                .emailId(emailId)
//                .subject(subject)
//                .body(body)
//                .dateSent(dateSent)
//                .senderId(sender.getUserId())
//                .priority(priority)
//                .isSpam(isSpam)
//                .build();
//    }
}
