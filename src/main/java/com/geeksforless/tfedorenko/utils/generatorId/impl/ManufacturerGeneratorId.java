package com.geeksforless.tfedorenko.utils.generatorId.impl;

import com.geeksforless.tfedorenko.data.ManufacturerContainer;
import com.geeksforless.tfedorenko.entity.Manufacturer;
import com.geeksforless.tfedorenko.utils.adapter.LocalDateTypeAdapter;
import com.geeksforless.tfedorenko.utils.generatorId.GeneratorId;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class ManufacturerGeneratorId implements GeneratorId<Manufacturer> {
    @Override
    public long generateId(String filename) {
        try (FileReader reader = new FileReader(filename)) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                    .create();
            ManufacturerContainer container = gson.fromJson(reader, ManufacturerContainer.class);
            List<Manufacturer> manufacturers = container.getEntity();

            if (manufacturers != null && !manufacturers.isEmpty()) {
                long maxId = manufacturers.stream()
                        .mapToLong(Manufacturer::getId)
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
