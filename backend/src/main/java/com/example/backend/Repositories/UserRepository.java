package com.example.backend.Repositories;


import com.example.backend.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User,Long> {

}
