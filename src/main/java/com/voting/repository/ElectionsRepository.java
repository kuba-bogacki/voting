package com.voting.repository;

import com.voting.model.entity.Elections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ElectionsRepository extends JpaRepository<Elections, UUID> {
}
