package com.voting.repository;

import com.voting.model.entity.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VoterRepository extends JpaRepository<Voter, UUID> {
    Voter findVoterByVoterEmail(String voterEmail);
}
