package com.example.lab1.plant;

import com.example.lab1.gardener.GardenerEntity;
import com.example.lab1.repository.Repository;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Dependent
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
    public List<PlantEntity> findAll() {
        return em.createQuery("select p from PlantEntity p", PlantEntity.class).getResultList();
    }

    @Override
    public void create(PlantEntity entity) {
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

    public Optional<PlantEntity> findByIdAndGardener(UUID id, GardenerEntity gardener){
        try {
            return Optional.of(em.createQuery("select p from PlantEntity p where p.id = :id and p.keeper = :gardener", PlantEntity.class)
                    .setParameter("gardener", gardener)
                    .setParameter("id", id)
                    .getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    public List<PlantEntity> findAllByGardener(GardenerEntity gardener){
        return em.createQuery("select p from PlantEntity p where p.keeper = :gardener", PlantEntity.class)
                .setParameter("gardener", gardener)
                .getResultList();
    }
}
