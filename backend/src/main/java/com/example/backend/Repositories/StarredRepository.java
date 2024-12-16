//package com.example.backend.Repositories;
//
//import com.example.backend.Entities.Email;
//import com.example.backend.Entities.Starred;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//
//@Repository
//public interface StarredRepository extends JpaRepository<Starred, Long> {
//
//    @Query("SELECT s.email.id FROM Starred s WHERE s.user.userId = :userId")
//    List<Long> findStarredEmailIdsByUserId(Long userId);
//
//}
