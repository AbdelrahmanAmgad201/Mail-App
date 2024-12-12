package com.example.backend.Repositories;

import com.example.backend.Entities.contacts;
import org.springframework.data.jpa.repository.JpaRepository;



public interface contactsRepository extends JpaRepository<contacts, Long> {

}
