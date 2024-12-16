package com.example.backend.Entities;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "email_metadata")
public class EmailMetadata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long metadataId;

    @Column(name = "is_trashed")
    private Boolean isTrashed;

    @Column(name = "date_trashed")
    private Date dateTrashed;

    private Priority priority;

    @Column(name = "is_spam")
    private Boolean isSpam;

    @Column(name = "is_starred") // did the sender star it
    private Boolean isStarred;

    @Column(name = "date_sent", nullable = false)
    private LocalDateTime dateSent;

    @OneToOne(mappedBy = "metadata")
    private Email email;
}
