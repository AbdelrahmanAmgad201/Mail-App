package com.example.backend.Repositories;

import com.example.backend.Entities.Contact;
import com.example.backend.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    // Use fetch join to eagerly load members
    @Query("SELECT DISTINCT c FROM Contact c " +
            "LEFT JOIN FETCH c.members m " +
            "LEFT JOIN FETCH m.member " +
            "WHERE c.owner = :owner")
    List<Contact> findByOwnerWithMembers(@Param("owner") User owner);

    @Query("SELECT DISTINCT c FROM Contact c " +
            "LEFT JOIN FETCH c.members m " +
            "LEFT JOIN FETCH m.member " +
            "WHERE c.owner = :owner AND c.contactName = :contactName")
    Optional<Contact> findByOwnerAndContactNameWithMembers(
            @Param("owner") User owner,
            @Param("contactName") String contactName
    );
}