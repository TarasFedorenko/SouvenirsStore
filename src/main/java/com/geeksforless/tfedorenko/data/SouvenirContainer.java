package com.geeksforless.tfedorenko.data;

import com.geeksforless.tfedorenko.entity.Souvenir;

import java.util.List;

public class SouvenirContainer implements EntityContainer<Souvenir> {
    private final List<Souvenir> souvenirs;

    public SouvenirContainer(List<Souvenir> souvenirs) {
        this.souvenirs = souvenirs;
    }

    @Override
    public List<Souvenir> getEntity() {
        return souvenirs;
    }
}
