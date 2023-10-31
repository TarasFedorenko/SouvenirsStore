package com.geeksforless.tfedorenko.utils.printer;

import java.util.Collection;

public class PrintHelper {

    public static <T> void printEntity(Collection<T> entities) {
        for (T entity : entities) {
            System.out.println(entity);
        }
    }
}
