package com.voting.service.implementation;

import com.voting.model.entity.Option;
import com.voting.repository.OptionRepository;
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
class FakeOptionRepository extends VotingSamples implements OptionRepository {

    private final Map<UUID, Option> inMemoryOptionDatabase = new HashMap<>() {{
        put(optionIdNo1, optionEntityNo1);
    }};
    private Option stubOption;

    public Option getStubOption() {
        return stubOption;
    }

    @Override
    public <S extends Option> S save(S entity) {
        if (entity == null) throw new IllegalArgumentException("Entity cannot be null");
        final var uuid = entity.getOptionId() == null ? UUID.randomUUID() : entity.getOptionId();
        final var updatedEntity = entity.toBuilder()
                .optionId(uuid)
                .build();
        this.inMemoryOptionDatabase.put(uuid, updatedEntity);
        this.stubOption = updatedEntity;
        return (S) updatedEntity;
    }

    @Override
    public Optional<Option> findById(UUID uuid) {
        return Optional.ofNullable(this.inMemoryOptionDatabase.get(uuid));
    }

    @Override
    public Option getById(UUID uuid) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public void flush() {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public <S extends Option> S saveAndFlush(S entity) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public <S extends Option> List<S> saveAllAndFlush(@NonNull Iterable<S> entities) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public void deleteAllInBatch(@NonNull Iterable<Option> entities) {
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
    public Option getOne(UUID uuid) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public Option getReferenceById(UUID uuid) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public <S extends Option> Optional<S> findOne(@NonNull Example<S> example) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public <S extends Option> List<S> findAll(@NonNull Example<S> example) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public <S extends Option> List<S> findAll(@NonNull Example<S> example, @NonNull Sort sort) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public <S extends Option> Page<S> findAll(@NonNull Example<S> example, @NonNull Pageable pageable) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public <S extends Option> long count(@NonNull Example<S> example) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public <S extends Option> boolean exists(@NonNull Example<S> example) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public <S extends Option, R> R findBy(@NonNull Example<S> example, @NonNull Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public <S extends Option> List<S> saveAll(@NonNull Iterable<S> entities) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public boolean existsById(UUID uuid) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public List<Option> findAll() {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public List<Option> findAllById(@NonNull Iterable<UUID> uuids) {
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
    public void delete(Option entity) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public void deleteAllById(@NonNull Iterable<? extends UUID> uuids) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public void deleteAll(@NonNull Iterable<? extends Option> entities) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public void deleteAll() {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public List<Option> findAll(@NonNull Sort sort) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }

    @Override
    public Page<Option> findAll(@NonNull Pageable pageable) {
        // No implementation for test purposes needed
        throw new NotImplementedException(NO_IMPLEMENTED_METHOD);
    }
}
