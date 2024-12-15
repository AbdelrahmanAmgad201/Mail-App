package com.example.backend.Entities;

import com.example.backend.DTO.ContactMemberDTO;
import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table(name = "contact_members")
@Builder
public class ContactMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contact_id", nullable = false)
    private Contact contact;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private User member;

    public ContactMember(Long id, Contact contact, User member) {
        this.id = id;
        this.contact = contact;
        this.member = member;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public User getMember() {
        return member;
    }

    public void setMember(User member) {
        this.member = member;
    }

//    public ContactMemberDTO toDTO() {
//        return ContactMemberDTO.builder()
//                .memberId(memberId)
//                .contactId(contact.getContactId())
//                .userId(member.getUserId())
//                .build();
//    }
}