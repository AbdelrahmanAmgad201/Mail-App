//package com.example.backend.Repositories;
//
//import com.example.backend.Entities.Receiver;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.Date;
//import java.util.List;
//
//
//public interface ReceiverRepository extends JpaRepository<Receiver, Long> {
//    @Query(value = """
//    SELECT r.*
//    FROM Receiver r
//    JOIN Email e ON r.email_id = e.id
//    WHERE r.receiver_id = :userId
//    AND r.is_trashed = false
//    AND e.metadata_is_spam = false
//    AND (:sender IS NULL OR e.sender_name LIKE %:sender%)
//    AND (:priority IS NULL OR e.metadata_priority = :priority)
//    AND (:subject IS NULL OR e.subject LIKE %:subject%)
//    AND (:startDate IS NULL OR e.metadata_date_sent >= :startDate)
//    AND (:endDate IS NULL OR e.metadata_date_sent <= :endDate)
//    ORDER BY
//        CASE WHEN :sortBy = 'date' THEN e.metadata_date_sent END :sortDirection,
//        CASE WHEN :sortBy = 'priority' THEN
//            CASE e.metadata_priority
//                WHEN 'HIGH' THEN 1
//                WHEN 'MEDIUM' THEN 2
//                WHEN 'LOW' THEN 3
//                ELSE 4
//            END
//        END :sortDirection,
//        e.metadata_date_sent :sortDirection
//    """, nativeQuery = true)
//    List<Receiver> findFilteredInboxEmailsNative(
//            @Param("userId") Long userId,
//            @Param("sender") String sender,
//            @Param("priority") String priority,
//            @Param("subject") String subject,
//            @Param("startDate") Date startDate,
//            @Param("endDate") Date endDate,
//            @Param("sortBy") String sortBy,
//            @Param("sortDirection") String sortDirection
//
//    );
//
//}
//
