package com.voting.model.entity;

import com.voting.model.type.VoterStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
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
}
