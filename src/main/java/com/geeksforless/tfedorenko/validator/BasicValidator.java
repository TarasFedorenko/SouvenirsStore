package com.geeksforless.tfedorenko.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BasicValidator {
    public static boolean validateId(Long id) {
        return id > 0;
    }

    public static boolean validateName(String name) {
        String pattern = "^[a-zA-Z0-9\\s\\-]+$";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(name);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
}
