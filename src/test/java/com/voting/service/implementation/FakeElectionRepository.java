package com.voting.service.implementation;

import com.voting.model.entity.Election;
import com.voting.repository.ElectionRepository;
import org.apache.commons.lang3.NotImplementedException;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.*;
import java.util.function.Function;

@SuppressWarnings("all")
class FakeElectionRepository extends VotingSamples implements ElectionRepository {

    private final Map<UUID, Election> inMemoryElectionDatabase = new HashMap<>() {{
        put(electionIdNo1, electionEntityNo1);
        put(electionIdNo3, electionEntityNo3);
    }};
    private Election stubElection;

    public Election getStubElection() {
        return stubElection;
    }

    @Override
    public Election findElectionByElectionName(String electionName) {
        return this.inMemoryElectionDatabase.values().stream()
                .filter(election -> election.getElectionName().equals(electionName))
                .findAny()
                .orElse(null);
    }

    @Override
    public <S extends Election> S save(S entity) {
        if (entity == null) throw new IllegalArgumentException("Entity cannot be null");
        final var uuid = entity.getElectionId() == null ? UUID.randomUUID() : entity.getElectionId();
        final var updatedEntity = entity.toBuilder()
                .electionId(uuid)
                .build();
        this.inMemoryElectionDatabase.put(uuid, updatedEntity);
        this.stubElection = updatedEntity;
        return (S) updatedEntity;
    }

    @Override
    public void flush() {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public <S extends Election> S saveAndFlush(S entity) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public <S extends Election> List<S> saveAllAndFlush(@NonNull Iterable<S> entities) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public void deleteAllInBatch(@NonNull Iterable<Election> entities) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public void deleteAllByIdInBatch(@NonNull Iterable<UUID> uuids) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public void deleteAllInBatch() {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public Election getOne(UUID uuid) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public Election getById(UUID uuid) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public Election getReferenceById(UUID uuid) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public <S extends Election> Optional<S> findOne(@NonNull Example<S> example) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public <S extends Election> List<S> findAll(@NonNull Example<S> example) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public <S extends Election> List<S> findAll(@NonNull Example<S> example, @NonNull Sort sort) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public <S extends Election> Page<S> findAll(@NonNull Example<S> example, @NonNull Pageable pageable) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public <S extends Election> long count(@NonNull Example<S> example) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public <S extends Election> boolean exists(@NonNull Example<S> example) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public <S extends Election, R> R findBy(@NonNull Example<S> example, @NonNull Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public <S extends Election> List<S> saveAll(@NonNull Iterable<S> entities) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public Optional<Election> findById(UUID uuid) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public boolean existsById(UUID uuid) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public List<Election> findAll() {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public List<Election> findAllById(@NonNull Iterable<UUID> uuids) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public long count() {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public void deleteById(UUID uuid) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public void delete(Election entity) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public void deleteAllById(@NonNull Iterable<? extends UUID> uuids) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public void deleteAll(@NonNull Iterable<? extends Election> entities) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public void deleteAll() {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public List<Election> findAll(@NonNull Sort sort) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public Page<Election> findAll(@NonNull Pageable pageable) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }
}
