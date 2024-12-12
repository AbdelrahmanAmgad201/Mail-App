package com.example.backend.Repositories;

import com.example.backend.Entities.Starred;
import org.springframework.data.jpa.repository.JpaRepository;



public interface StarredRepository extends JpaRepository<Starred, Long> {

}
