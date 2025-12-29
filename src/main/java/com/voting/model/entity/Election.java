package com.voting.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Election {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID electionId;

    @Column(nullable = false, unique = true, length = 64)
    private String electionName;

    @Builder.Default
    @OneToMany(mappedBy = "election", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Option> electionOptions = new HashSet<>();

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn
    private Set<Voter> electionVoters = new HashSet<>();

    public void addOption(Option option) {
        this.electionOptions.add(option);
        option.setElection(this);
    }

    public boolean isOptionNameNotExist(String optionName) {
        return this.electionOptions.stream()
                .noneMatch(option -> option.getOptionName().equalsIgnoreCase(optionName));
    }

    public UUID getOptionId(String optionName) {
        return this.electionOptions.stream()
                .filter(option -> option.getOptionName().equalsIgnoreCase(optionName))
                .findAny()
                .orElseThrow()
                .getOptionId();
    }

    public void addVoter(Voter voter) {
        this.electionVoters.add(voter);
    }

    public boolean hasAlreadyVoted(Voter voter) {
        return this.electionVoters.stream()
                .anyMatch(electionVoter -> electionVoter.equals(voter));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Election other)) return false;
        return this.electionId != null && this.electionId.equals(other.getElectionId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
