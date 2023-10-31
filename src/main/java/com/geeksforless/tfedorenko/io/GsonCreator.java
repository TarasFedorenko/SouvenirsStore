package com.geeksforless.tfedorenko.io;

import com.geeksforless.tfedorenko.utils.adapter.LocalDateTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDate;

public class GsonCreator {
    public Gson createGson() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();
    }
}
