package com.example.backend.Services;

import com.example.backend.Entities.Contact;
import com.example.backend.Entities.ContactMember;
import com.example.backend.Entities.User;
import com.example.backend.Repositories.ContactMemberRepository;
import com.example.backend.Repositories.ContactRepository;
import com.example.backend.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final ContactMemberRepository contactMemberRepository;
    private final UserRepository userRepository;

    public ContactService(ContactRepository contactRepository, ContactMemberRepository contactMemberRepository, UserRepository userRepository) {
        this.contactRepository = contactRepository;
        this.contactMemberRepository = contactMemberRepository;
        this.userRepository = userRepository;
    }

    // Create a new contact
    public Contact createContact(Long ownerId, String contactName) {

        User owner = userRepository.findById(ownerId);

        Contact contact = Contact.builder()
                .owner(owner)
                .contactName(contactName)
                .build();

        return contactRepository.save(contact);
    }


    public ContactMember addMemberToContact(Long contactId, Long memberId) {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new RuntimeException("Contact not found"));

        User member = userRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ContactMember contactMember = ContactMember.builder()
                .contact(contact)
                .member(member)
                .build();

        return contactMemberRepository.save(contactMember);
    }

    public List<ContactMember> getAllMembers(Long contactId) {

        contactRepository.findById(contactId)
                .orElseThrow(() -> new RuntimeException("Contact not found"));


        return contactMemberRepository.findByContact_ContactId(contactId);
    }


    public void removeMemberFromContact(Long contactId, Long memberId) {

        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new RuntimeException("Contact not found"));


        User member = userRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional<ContactMember> contactMember = contactMemberRepository.findByContactAndMember(contact, member);
        if (contactMember.isPresent()) {
            contactMemberRepository.delete(contactMember.get());
        } else {
            throw new RuntimeException("Member not found in the contact");
        }
    }

    public void deleteContact(Long contactId) {

        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new RuntimeException("Contact not found"));


        contactRepository.delete(contact);
    }
}
