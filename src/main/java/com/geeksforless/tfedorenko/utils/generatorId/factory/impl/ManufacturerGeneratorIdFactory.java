package com.geeksforless.tfedorenko.utils.generatorId.factory.impl;

import com.geeksforless.tfedorenko.entity.Manufacturer;
import com.geeksforless.tfedorenko.utils.generatorId.GeneratorId;
import com.geeksforless.tfedorenko.utils.generatorId.factory.GeneratorIdFactory;
import com.geeksforless.tfedorenko.utils.generatorId.impl.ManufacturerGeneratorId;

public class ManufacturerGeneratorIdFactory implements GeneratorIdFactory<Manufacturer> {
    @Override
    public GeneratorId<Manufacturer> createGenerator() {
        return new ManufacturerGeneratorId();
    }
}
