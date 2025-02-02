package com.example.lab1.species;

import com.example.lab1.repository.Repository;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Dependent
public class SpeciesRepository implements Repository<SpeciesEntity, UUID> {
    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<SpeciesEntity> find(UUID id) {
        return Optional.ofNullable(em.find(SpeciesEntity.class, id));
    }

    @Override
    public List<SpeciesEntity> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<SpeciesEntity> query = cb.createQuery(SpeciesEntity.class);
        Root<SpeciesEntity> root = query.from(SpeciesEntity.class);
        query.select(root);
        return em.createQuery(query).getResultList();
//        return em.createQuery("select s from SpeciesEntity s", SpeciesEntity.class).getResultList();
    }

    @Override
    public void create(SpeciesEntity entity) {
        em.persist(entity);
    }

    @Override
    public void delete(UUID id) {
        em.remove(em.find(SpeciesEntity.class, id));
    }

    @Override
    public void update(UUID id, SpeciesEntity entity) {
        em.merge(entity);
    }
}
