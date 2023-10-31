package com.geeksforless.tfedorenko.io.souvenirIO;

import com.geeksforless.tfedorenko.data.SouvenirContainer;
import com.geeksforless.tfedorenko.entity.Souvenir;

import com.geeksforless.tfedorenko.io.GsonCreator;
import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

public class SouvenirIO {
    private static SouvenirIO instance;
    private final GsonCreator creator = new GsonCreator();
    private final String filePath = "souvenirs.json";

    private SouvenirIO() {
    }

    public static SouvenirIO getInstance() {
        if (instance == null) {
            instance = new SouvenirIO();
        }
        return instance;
    }

    public void updateSouvenirsInFile(Consumer<List<Souvenir>> updateFunction) {
        Gson gson = creator.createGson();
        try (FileReader reader = new FileReader(filePath)) {
            SouvenirContainer souvenirsContainer = gson.fromJson(reader, SouvenirContainer.class);
            updateFunction.accept(souvenirsContainer.getEntity());
            writeSouvenirsToFile(souvenirsContainer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Souvenir> readSouvenirsFromFile() {
        Gson gson = creator.createGson();
        try (FileReader reader = new FileReader(filePath)) {
            SouvenirContainer souvenirsContainer = gson.fromJson(reader, SouvenirContainer.class);
            return souvenirsContainer.getEntity();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void writeSouvenirsToFile(SouvenirContainer souvenirsContainer) {
        Gson gson = creator.createGson();
        try (FileWriter writer = new FileWriter(filePath)) {
            String json = gson.toJson(souvenirsContainer);
            writer.write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
