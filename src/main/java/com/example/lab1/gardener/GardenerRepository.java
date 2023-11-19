package com.example.lab1.gardener;

import com.example.lab1.repository.Repository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.*;

@RequestScoped
public class GardenerRepository implements Repository<GardenerEntity, UUID> {

    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<GardenerEntity> find(UUID id) {
        return Optional.ofNullable(em.find(GardenerEntity.class, id));
    }

    @Override
    public List<GardenerEntity> findAll() {
        return em.createQuery("select g from GardenerEntity g", GardenerEntity.class).getResultList();
    }

    @Override
    public void create(GardenerEntity entity) {
        em.persist(entity);
    }

    @Override
    public void delete(UUID id) {
        em.remove(em.find(GardenerEntity.class, id));
    }

    @Override
    public void update(UUID id, GardenerEntity entity) {
        em.merge(entity);
    }
}
