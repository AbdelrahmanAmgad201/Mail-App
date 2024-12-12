package com.example.backend.Entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactId;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    private String contactName;

    @OneToMany(mappedBy = "contact")
    private Set<ContactMember> members;

    public Contact(Long contactId, User owner, String contactName, Set<ContactMember> members) {
        this.contactId = contactId;
        this.owner = owner;
        this.contactName = contactName;
        this.members = members;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public Set<ContactMember> getMembers() {
        return members;
    }

    public void setMembers(Set<ContactMember> members) {
        this.members = members;
    }
}
