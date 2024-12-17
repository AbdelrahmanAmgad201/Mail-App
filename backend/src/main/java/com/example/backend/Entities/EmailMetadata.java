package com.example.backend.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "email_metadata")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailMetadata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long metadataId;

    @Column(name = "is_trashed")
    private Boolean isTrashed;

    @Column(name = "date_trashed")
    private Date dateTrashed;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private Priority priority;

    @Column(name = "is_spam")
    private Boolean isSpam;

    @Column(name = "date_sent", nullable = false)
    private LocalDateTime dateSent;

    @OneToOne(mappedBy = "metadata")
    @JsonIgnore
    private Email email;

    // Override equals and hashCode to break circular reference
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailMetadata that = (EmailMetadata) o;
        return Objects.equals(metadataId, that.metadataId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(metadataId);
    }
}