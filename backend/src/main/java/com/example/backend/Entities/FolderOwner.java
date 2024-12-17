package com.example.backend.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "folder_owners")

public class FolderOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long folderId;

    @Column(name = "folder_name")
    private String folderName;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "folder")
    @JsonManagedReference
    private Set<FolderMember> members;

    @OneToMany(mappedBy = "folder")
    @JsonManagedReference
    private Set<FolderBySubject> filters;


}

