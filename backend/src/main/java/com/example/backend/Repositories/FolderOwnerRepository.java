package com.example.backend.Repositories;

import com.example.backend.Entities.FolderOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FolderOwnerRepository extends JpaRepository<FolderOwner, Long> {
    @Query("SELECT fo FROM FolderOwner fo WHERE fo.folderName = :folderName AND fo.user.userId = :userId")
    FolderOwner findByFolderNameAndUserId(String folderName, Long userId);

    List<FolderOwner> findByUserUserId(Long userId);
}