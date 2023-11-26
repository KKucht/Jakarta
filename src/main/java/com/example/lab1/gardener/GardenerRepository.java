package com.example.lab1.gardener;

import com.example.lab1.repository.Repository;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Dependent
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

    public Optional<GardenerEntity> findByLogin(String login) {
        try {
            return Optional.of(em.createQuery("select u from GardenerEntity u where u.login = :login", GardenerEntity.class)
                    .setParameter("login", login)
                    .getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }
}
