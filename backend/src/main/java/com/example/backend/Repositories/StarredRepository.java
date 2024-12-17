package com.example.backend.Repositories;

import com.example.backend.Entities.Email;
import com.example.backend.Entities.Starred;
import com.example.backend.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface StarredRepository extends JpaRepository<Starred, Long> {

    Optional<Starred> findByUserAndEmail(User user, Email email);

}
