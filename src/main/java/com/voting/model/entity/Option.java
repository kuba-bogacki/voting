package com.voting.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID optionId;

    @Column(nullable = false, length = 64)
    private String optionName;

    @Setter
    private long optionVotes;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    private Election election;
}
