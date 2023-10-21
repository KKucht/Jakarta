package com.example.lab1.species;

import com.example.lab1.dataStore.DataStore;
import com.example.lab1.repository.Repository;
import jakarta.inject.Inject;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class SpeciesRepository implements Repository<SpeciesEntity, UUID> {
    private final DataStore dataStore;

    @Inject
    public SpeciesRepository(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public Optional<SpeciesEntity> find(UUID id) {
        return dataStore.getSpecies()
                .stream()
                .filter(entity -> entity.getId().equals(id))
                .findFirst();
    }

    @Override
    public Set<SpeciesEntity> findAll() {
        return new HashSet<>(dataStore.getSpecies());
    }

    @Override
    public void create(SpeciesEntity entity) {
        dataStore.getSpecies().add(new SpeciesEntity(entity));
    }

    @Override
    public void delete(UUID id) {
        dataStore.getSpecies()
                .stream()
                .filter(entity -> entity.getId().equals(id))
                .findFirst()
                .ifPresent(entity -> dataStore.getSpecies().remove(entity));
    }

    @Override
    public void update(UUID id, SpeciesEntity entity) {
        dataStore.getSpecies()
                .stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .ifPresent(e -> dataStore.getSpecies().remove(e));
        dataStore.getSpecies().add(entity);
    }
}
