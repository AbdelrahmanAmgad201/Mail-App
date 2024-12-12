package com.example.backend.Entities;

import com.example.backend.DTO.FolderBySubjectDTO;
import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table(name = "folder_by_subject")
@Builder
public class FolderBySubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "folder_id", nullable = false)
    private FolderOwner folder;

    private String filterSubject;

    public FolderBySubject(String filterSubject, FolderOwner folder, Long id) {
        this.filterSubject = filterSubject;
        this.folder = folder;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FolderOwner getFolder() {
        return folder;
    }

    public void setFolder(FolderOwner folder) {
        this.folder = folder;
    }

    public String getFilterSubject() {
        return filterSubject;
    }

    public void setFilterSubject(String filterSubject) {
        this.filterSubject = filterSubject;
    }

//    public FolderBySubjectDTO toDTO() {
//        return FolderBySubjectDTO.builder()
//                .folderId(folder.getId())  // Assuming FolderOwner has getId method
//                .filterSubject(this.filterSubject)
//                .build();
//    }
}