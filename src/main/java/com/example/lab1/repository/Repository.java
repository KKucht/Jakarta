package com.example.lab1.repository;

import java.util.Optional;
import java.util.Set;

public interface Repository<E, K> {

    Optional<E> find(K id);

    Set<E> findAll();

    void create(E entity);

    void delete(K id);

    void update(K id, E entity);

}
