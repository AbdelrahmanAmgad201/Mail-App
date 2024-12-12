package com.example.backend.Repositories;

import com.example.backend.Entities.Email;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmailRepository extends JpaRepository<Email, Long> {

}
