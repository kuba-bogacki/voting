package com.voting.model.entity;

import com.voting.model.type.VoterStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Voter {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID voterId;

    @Email
    @Column(nullable = false, unique = true)
    private String voterEmail;

    @Column(nullable = false, length = 64)
    private String voterFirstName;

    @Column(nullable = false, length = 64)
    private String voterLastName;

    @Setter
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private VoterStatus voterStatus = VoterStatus.UNBLOCKED;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Voter other)) return false;
        return this.voterId != null && this.voterId.equals(other.getVoterId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
