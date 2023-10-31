package com.geeksforless.tfedorenko.utils.init;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateFileAndInit {
    public static void createInitSouvenir(String path) {
        File file = new File(path);
        try {
            if (!file.exists() && file.createNewFile()) {
                FileWriter fileWriter = new FileWriter(path);
                fileWriter.write("{\"souvenirs\":[]}");
                fileWriter.flush();
                fileWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createInitManufacturer(String path) {
        File file = new File(path);
        try {
            if (!file.exists() && file.createNewFile()) {
                FileWriter fileWriter = new FileWriter(path);
                fileWriter.write("{\"manufacturers\":[]}");
                fileWriter.flush();
                fileWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
