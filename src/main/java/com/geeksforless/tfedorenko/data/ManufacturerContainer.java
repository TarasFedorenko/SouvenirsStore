package com.geeksforless.tfedorenko.data;

import com.geeksforless.tfedorenko.entity.Manufacturer;

import java.util.List;

public class ManufacturerContainer implements EntityContainer<Manufacturer> {
    private final List<Manufacturer> manufacturers;

    public ManufacturerContainer(List<Manufacturer> manufacturers) {
        this.manufacturers = manufacturers;
    }

    @Override
    public List<Manufacturer> getEntity() {
        return manufacturers;
    }
}
