//package com.example.backend.Repositories;
//
//import com.example.backend.Entities.Email;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//
//
//public interface EmailRepository extends JpaRepository<Email, Long> {
//
//    // Custom query to find emails in a folder, filtered by member (user) and subject
//    List<Email> findByFolder_IdAndReceivers_User_IdAndSubject(Long folderId, Long userId, String subject);
//}
