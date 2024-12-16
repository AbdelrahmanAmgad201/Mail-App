package com.example.backend.Entities;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.Set;

@Entity
@Table(name = "folder_owners")

public class FolderOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long folderId;

    private String folderName;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "folder")
    private Set<FolderMember> members;

    @OneToMany(mappedBy = "folder")
    private Set<FolderBySubject> filters;

    public FolderOwner(Long folderId, String folderName, User user, Set<FolderMember> members, Set<FolderBySubject> filters) {
        this.folderId = folderId;
        this.folderName = folderName;
        this.user = user;
        this.members = members;
        this.filters = filters;
    }

    public Long getFolderId() {
        return folderId;
    }

    public void setFolderId(Long folderId) {
        this.folderId = folderId;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<FolderMember> getMembers() {
        return members;
    }

    public void setMembers(Set<FolderMember> members) {
        this.members = members;
    }

    public Set<FolderBySubject> getFilters() {
        return filters;
    }

    public void setFilters(Set<FolderBySubject> filters) {
        this.filters = filters;
    }

}

