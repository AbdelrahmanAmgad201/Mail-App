package com.example.backend.Entities;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table(name = "folder_members")

public class FolderMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "folder_id", nullable = false)
    private FolderOwner folder;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public FolderMember(Long id, FolderOwner folder, User user) {
        this.id = id;
        this.folder = folder;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
