package com.example.backend.Services;

import com.example.backend.DTO.ContactDTO;
import com.example.backend.Entities.Contact;
import com.example.backend.Entities.ContactMember;
import com.example.backend.Entities.User;
import com.example.backend.Repositories.ContactRepository;
import com.example.backend.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;
    private final UserRepository userRepository;

    @Transactional
    public ContactDTO createContact(Long ownerId, String contactName, List<String> emailAddresses) {
        // Find the owner
        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        // Create contact
        Contact contact = new Contact();
        contact.setOwner(owner);
        contact.setContactName(contactName);

        // Create a new HashSet to avoid Hibernate's PersistentSet issues
        Set<ContactMember> members = new HashSet<>();
        for (String email : emailAddresses) {
            User member = userRepository.findByEmailAddress(email)
                    .orElseThrow(() -> new RuntimeException("User with email " + email + " not found"));

            ContactMember contactMember = new ContactMember();
            contactMember.setContact(contact);
            contactMember.setMember(member);
            members.add(contactMember);
        }

        contact.setMembers(members);
        Contact savedContact = contactRepository.save(contact);

        return mapToContactDTO(savedContact);
    }

    @Transactional(readOnly = true)
    public List<ContactDTO> getAllContacts(Long ownerId) {
        // Find the owner
        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        // Retrieve all contacts for the owner
        List<Contact> contacts = contactRepository.findAll().stream()
                .filter(contact -> contact.getOwner().getUserId().equals(ownerId))
                .collect(Collectors.toList());

        return contacts.stream()
                .map(this::mapToContactDTO)
                .collect(Collectors.toList());
    }

    private ContactDTO mapToContactDTO(Contact contact) {
        // Careful mapping to avoid lazy loading issues
        List<ContactDTO.ContactMemberDTO> memberDTOs = new ArrayList<>();
        for (ContactMember member : contact.getMembers()) {
            memberDTOs.add(ContactDTO.ContactMemberDTO.builder()
                    .memberId(member.getMember().getUserId())
                    .emailAddress(member.getMember().getEmailAddress())
                    .firstName(member.getMember().getFirstName())
                    .lastName(member.getMember().getLastName())
                    .build());
        }

        return ContactDTO.builder()
                .contactId(contact.getContactId())
                .contactName(contact.getContactName())
                .members(memberDTOs)
                .build();
    }
}