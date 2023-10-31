package com.geeksforless.tfedorenko.utils.generatorId.impl;

import com.geeksforless.tfedorenko.data.SouvenirContainer;
import com.geeksforless.tfedorenko.entity.Souvenir;
import com.geeksforless.tfedorenko.utils.adapter.LocalDateTypeAdapter;
import com.geeksforless.tfedorenko.utils.generatorId.GeneratorId;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class SouvenirGeneratorId implements GeneratorId<Souvenir> {

    @Override
    public long generateId(String filename) {
        try (FileReader reader = new FileReader(filename)) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                    .create();
            SouvenirContainer container = gson.fromJson(reader, SouvenirContainer.class);
            List<Souvenir> souvenirs = container.getEntity();
            if (souvenirs != null && !souvenirs.isEmpty()) {
                long maxId = souvenirs.stream()
                        .mapToLong(Souvenir::getId)
                        .max()
                        .orElse(0);
                return maxId + 1;
            } else {
                return 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
}


