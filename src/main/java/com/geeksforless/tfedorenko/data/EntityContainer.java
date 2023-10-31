package com.geeksforless.tfedorenko.data;

import com.geeksforless.tfedorenko.entity.BaseEntity;

import java.util.List;

public interface EntityContainer<E extends BaseEntity> {
    List<E> getEntity();
}
