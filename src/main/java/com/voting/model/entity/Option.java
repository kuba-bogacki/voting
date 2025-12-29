package com.voting.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Builder(toBuilder = true)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Option other)) return false;
        return this.optionId != null && this.optionId.equals(other.getOptionId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
