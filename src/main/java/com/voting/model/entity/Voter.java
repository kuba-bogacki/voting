package com.voting.model.entity;

import com.voting.model.type.VoterStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Size(min = 2, max = 64)
    @Column(nullable = false)
    private String voterFirstName;

    @Size(min = 2, max = 64)
    @Column(nullable = false)
    private String voterLastName;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private VoterStatus voterStatus = VoterStatus.UNBLOCKED;
}
