package com.example.lab1.plant;

import com.example.lab1.dataStore.DataStore;
import com.example.lab1.repository.Repository;
import com.example.lab1.species.SpeciesEntity;
import jakarta.inject.Inject;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class PlantRepository implements Repository<PlantEntity, UUID> {
    private final DataStore dataStore;

    @Inject
    public PlantRepository(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public Optional<PlantEntity> find(UUID id) {
        return dataStore.getPlants()
                .stream()
                .filter(entity -> entity.getId().equals(id))
                .findFirst();
    }

    @Override
    public Set<PlantEntity> findAll()  {
        return new HashSet<>(dataStore.getPlants());
    }

    @Override
    public void create(PlantEntity entity)  {
        dataStore.getPlants().add(new PlantEntity(entity));
    }

    @Override
    public void delete(UUID id) {
        dataStore.getPlants()
                .stream()
                .filter(entity -> entity.getId().equals(id))
                .findFirst()
                .ifPresent(entity -> dataStore.getPlants().remove(entity));
    }

    @Override
    public void update(UUID id, PlantEntity entity) {
        dataStore.getPlants()
                .stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .ifPresent(e -> dataStore.getPlants().remove(e));
        dataStore.getPlants().add(entity);
    }
}
