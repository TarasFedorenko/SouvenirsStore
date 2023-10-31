package com.geeksforless.tfedorenko.dao;

import com.geeksforless.tfedorenko.entity.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface CRUDInterface<E extends BaseEntity> {

    void create(E entity);

    void update(E entity);

    void delete(Long id);

    Optional<E> findById(Long id);

    List<E> findAll();
}
