package com.example.backend.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "receivers")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Receiver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email_id", nullable = false)
    @JsonIgnore
    private Email email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;

    @Column(name = "is_read")
    private Boolean isRead;

    @Column(name = "is_trashed")
    private Boolean isTrashed;

    @Column(name = "date_trashed")
    private Date dateTrashed;

    // Override equals and hashCode to break circular reference
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receiver receiver = (Receiver) o;
        return Objects.equals(id, receiver.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}