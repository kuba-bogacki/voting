package com.voting.repository;

import com.voting.model.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface OptionRepository extends JpaRepository<Option, UUID> {
    @Query(value = "SELECT o FROM Option o where o.election.electionId = ?1")
    Set<Option> findOptionsByElectionId(UUID electionId);
}
