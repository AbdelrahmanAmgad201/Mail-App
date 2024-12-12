package com.example.backend.Entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String emailAddress;

    @Column(nullable = false)
    private String passwordHash;

    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "owner")
    private Set<Contact> contacts;

    @OneToMany(mappedBy = "user")
    private Set<Starred> starredEmails;

    public User(Long userId, String emailAddress, String passwordHash, String firstName, String lastName, Set<Contact> contacts, Set<Starred> starredEmails) {
        this.userId = userId;
        this.emailAddress = emailAddress;
        this.passwordHash = passwordHash;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contacts = contacts;
        this.starredEmails = starredEmails;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    public Set<Starred> getStarredEmails() {
        return starredEmails;
    }

    public void setStarredEmails(Set<Starred> starredEmails) {
        this.starredEmails = starredEmails;
    }
}
