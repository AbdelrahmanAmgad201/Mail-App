package com.example.backend.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backend.Entities.user;


public interface userRepository extends JpaRepository<user,Long> {

}
