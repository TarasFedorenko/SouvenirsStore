package com.geeksforless.tfedorenko.io.manufacturerIO;

import com.geeksforless.tfedorenko.data.ManufacturerContainer;
import com.geeksforless.tfedorenko.entity.Manufacturer;
import com.geeksforless.tfedorenko.io.GsonCreator;
import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

public class ManufacturerIO {
    private ManufacturerIO() {
    }

    public static ManufacturerIO getInstance() {
        if (instance == null) {
            instance = new ManufacturerIO();
        }
        return instance;
    }

    private static ManufacturerIO instance;
    private final GsonCreator creator = new GsonCreator();
    private final String filePath = "manufacturers.json";

    public void updateManufacturersInFile(Consumer<List<Manufacturer>> updateFunction) {
        Gson gson = creator.createGson();
        try (FileReader reader = new FileReader(filePath)) {
            ManufacturerContainer container = gson.fromJson(reader, ManufacturerContainer.class);
            updateFunction.accept(container.getEntity());
            writeManufacturersToFile(container);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Manufacturer> readManufacturersFromFile() {
        Gson gson = creator.createGson();
        try (FileReader reader = new FileReader(filePath)) {
            ManufacturerContainer container = gson.fromJson(reader, ManufacturerContainer.class);
            return container.getEntity();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeManufacturersToFile(ManufacturerContainer container) {
        Gson gson = creator.createGson();
        try (FileWriter writer = new FileWriter(filePath)) {
            String json = gson.toJson(container);
            writer.write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
