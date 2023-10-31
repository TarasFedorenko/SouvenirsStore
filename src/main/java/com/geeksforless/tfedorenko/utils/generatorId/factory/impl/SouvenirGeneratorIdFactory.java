package com.geeksforless.tfedorenko.utils.generatorId.factory.impl;

import com.geeksforless.tfedorenko.entity.Souvenir;
import com.geeksforless.tfedorenko.utils.generatorId.GeneratorId;
import com.geeksforless.tfedorenko.utils.generatorId.factory.GeneratorIdFactory;
import com.geeksforless.tfedorenko.utils.generatorId.impl.SouvenirGeneratorId;

public class SouvenirGeneratorIdFactory implements GeneratorIdFactory<Souvenir> {
    @Override
    public GeneratorId<Souvenir> createGenerator() {
        return new SouvenirGeneratorId();
    }
}
