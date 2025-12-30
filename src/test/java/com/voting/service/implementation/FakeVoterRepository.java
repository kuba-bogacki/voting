package com.voting.service.implementation;

import com.voting.model.entity.Voter;
import com.voting.repository.VoterRepository;
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
class FakeVoterRepository extends VotingSamples implements VoterRepository {

    private final Map<UUID, Voter> inMemoryVoterDatabase = new HashMap<>() {{
        put(voterIdNo1, voterEntityNo1);
    }};
    private Voter stubVoter;

    public Voter getStubVoter() {
        return stubVoter;
    }

    @Override
    public Voter findVoterByVoterEmail(String voterEmail) {
        return this.inMemoryVoterDatabase.values().stream()
                .filter(voter -> voter.getVoterEmail().equals(voterEmail))
                .findAny()
                .orElse(null);
    }

    @Override
    public <S extends Voter> S save(S entity) {
        final var uuid = entity.getVoterId() == null ? UUID.randomUUID() : entity.getVoterId();
        final var updatedEntity = entity.toBuilder()
                .voterId(uuid)
                .build();
        this.inMemoryVoterDatabase.put(uuid, updatedEntity);
        this.stubVoter = updatedEntity;
        return (S) updatedEntity;
    }

    @Override
    public void flush() {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public <S extends Voter> S saveAndFlush(S entity) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public <S extends Voter> List<S> saveAllAndFlush(@NonNull Iterable<S> entities) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public void deleteAllInBatch(@NonNull Iterable<Voter> entities) {
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
    public Voter getOne(UUID uuid) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public Voter getById(UUID uuid) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public Voter getReferenceById(UUID uuid) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public <S extends Voter> Optional<S> findOne(@NonNull Example<S> example) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public <S extends Voter> List<S> findAll(@NonNull Example<S> example) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public <S extends Voter> List<S> findAll(@NonNull Example<S> example, @NonNull Sort sort) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public <S extends Voter> Page<S> findAll(@NonNull Example<S> example, @NonNull Pageable pageable) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public <S extends Voter> long count(@NonNull Example<S> example) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public <S extends Voter> boolean exists(@NonNull Example<S> example) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public <S extends Voter, R> R findBy(@NonNull Example<S> example, @NonNull Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public <S extends Voter> List<S> saveAll(@NonNull Iterable<S> entities) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public Optional<Voter> findById(UUID uuid) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public boolean existsById(UUID uuid) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public List<Voter> findAll() {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public List<Voter> findAllById(@NonNull Iterable<UUID> uuids) {
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
    public void delete(Voter entity) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public void deleteAllById(@NonNull Iterable<? extends UUID> uuids) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public void deleteAll(@NonNull Iterable<? extends Voter> entities) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public void deleteAll() {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public List<Voter> findAll(@NonNull Sort sort) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public Page<Voter> findAll(@NonNull Pageable pageable) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }
}
