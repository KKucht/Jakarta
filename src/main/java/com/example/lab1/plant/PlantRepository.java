package com.example.lab1.plant;

import com.example.lab1.dataStore.DataStore;
import com.example.lab1.repository.Repository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.*;

@RequestScoped
public class PlantRepository implements Repository<PlantEntity, UUID> {
    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<PlantEntity> find(UUID id) {
        return Optional.ofNullable(em.find(PlantEntity.class, id));
    }

    @Override
    public List<PlantEntity> findAll()  {
        return em.createQuery("select p from PlantEntity p", PlantEntity.class).getResultList();
    }

    @Override
    public void create(PlantEntity entity)  {
        em.persist(entity);
    }

    @Override
    public void delete(UUID id) {
        em.remove(em.find(PlantEntity.class, id));
    }

    @Override
    public void update(UUID id, PlantEntity entity) {
        em.merge(entity);
    }
}
