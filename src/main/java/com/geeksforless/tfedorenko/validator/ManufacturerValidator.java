package com.geeksforless.tfedorenko.validator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class ManufacturerValidator extends BasicValidator{

    public static boolean validateCountryName(String countryName) {
        String pattern = "^[a-zA-Z\\s\\-]+$";
        if (countryName.length() < 2) {
            return false;
        }
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(countryName);
        return matcher.matches();
    }
}
