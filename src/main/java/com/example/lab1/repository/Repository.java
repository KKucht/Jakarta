package com.example.lab1.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<E, K> {

    Optional<E> find(K id);

    List<E> findAll();

    void create(E entity);

    void delete(K id);

    void update(K id, E entity);

}
