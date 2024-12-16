package com.example.backend.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "folder_by_subject")

public class FolderBySubject {

    @Id

    private Long id;

    @ManyToOne
    @JoinColumn(name = "folder_id", nullable = false)
    private FolderOwner folder;

    @Column(name = "filter_subject")
    private String filterSubject;

}