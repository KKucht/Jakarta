package com.example.lab1.gardener;

import com.example.lab1.dataStore.DataStore;
import com.example.lab1.repository.Repository;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.*;

public class GardenerRepository implements Repository<GardenerEntity, UUID> {

    private final DataStore dataStore;

    @Inject
    public GardenerRepository(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public Optional<GardenerEntity> find(UUID id) {
        return dataStore.getGardeners()
                .stream()
                .filter(entity -> entity.getId().equals(id))
                .findFirst();
    }

    @Override
    public Set<GardenerEntity> findAll() {
        return new HashSet<>(dataStore.getGardeners());
    }

    @Override
    public void create(GardenerEntity entity) {
        dataStore.getGardeners().add(new GardenerEntity(entity));
    }

    @Override
    public void delete(UUID id) {
        dataStore.getGardeners()
                .stream()
                .filter(entity -> entity.getId().equals(id))
                .findFirst()
                .ifPresent(entity -> dataStore.getGardeners().remove(entity));
    }

    @Override
    public void update(UUID id, GardenerEntity entity) {
        dataStore.getGardeners()
                .stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .ifPresent(e -> dataStore.getGardeners().remove(e));
        dataStore.getGardeners().add(entity);
    }
}
