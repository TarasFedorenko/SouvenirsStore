package com.geeksforless.tfedorenko.utils.generatorId;

import com.geeksforless.tfedorenko.entity.BaseEntity;

public interface GeneratorId<E extends BaseEntity> {

    long generateId(String filename);
}
