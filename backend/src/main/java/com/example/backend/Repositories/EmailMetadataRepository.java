package com.example.backend.Repositories;

import com.example.backend.Entities.EmailMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailMetadataRepository extends JpaRepository<EmailMetadata, Long> {
}
