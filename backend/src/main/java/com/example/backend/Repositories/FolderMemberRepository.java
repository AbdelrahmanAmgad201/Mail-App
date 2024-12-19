package com.example.backend.Repositories;

import com.example.backend.Entities.FolderMember;
import com.example.backend.Entities.FolderOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderMemberRepository extends JpaRepository<FolderMember, Long> {

}