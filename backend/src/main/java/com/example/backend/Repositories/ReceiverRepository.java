package com.example.backend.Repositories;

import com.example.backend.Entities.Email;
import com.example.backend.Entities.Receiver;
import com.example.backend.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReceiverRepository extends JpaRepository<Receiver, Long> {

    // Changed to find by the receiver user


    // Native query remains the same
    @Query(value = """
    SELECT r.*, e.*, em.* 
    FROM receivers r 
    JOIN emails e ON r.email_id = e.email_id 
    JOIN email_metadata em ON e.metadata_id = em.metadata_id 
    WHERE r.receiver_id = :userId 
""", nativeQuery = true)
    List<Object[]> findFilteredInboxEmailsNative(@Param("userId") Long userId);


    List<Receiver> findByReceiver(User receiver);

    // Find all receivers for a specific email
    List<Receiver> findByEmail(Email email);


    @Query("SELECT DISTINCT e FROM Email e WHERE e.sender = :user")
    List<Email> findEmailsBySender(@Param("user") User user);

    @Query("SELECT r FROM Receiver r WHERE r.receiver.userId = :userId AND r.email.emailId = :emailId")
    Optional<Receiver> findByReceiverUserIdAndEmailId(
            @Param("userId") Long userId,
            @Param("emailId") Long emailId
    );


}