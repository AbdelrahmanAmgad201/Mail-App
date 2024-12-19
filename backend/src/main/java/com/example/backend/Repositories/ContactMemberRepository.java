package com.example.backend.Repositories;

import com.example.backend.Entities.ContactMember;
import com.example.backend.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactMemberRepository extends JpaRepository<ContactMember, Long> {
    List<ContactMember> findByContact_ContactId(Long contactId);
}