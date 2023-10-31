package com.geeksforless.tfedorenko.utils.generatorId.factory;

import com.geeksforless.tfedorenko.entity.BaseEntity;
import com.geeksforless.tfedorenko.utils.generatorId.GeneratorId;

public interface GeneratorIdFactory<T extends BaseEntity> {
    GeneratorId<T> createGenerator();
}
