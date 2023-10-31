package com.geeksforless.tfedorenko.validator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SouvenirValidator extends BasicValidator {

    public static boolean validateDate(String date) {
        String pattern = "^(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(date);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean validatePrice(String priceStr) {
        try {
            BigDecimal price = new BigDecimal(priceStr);
            price = price.setScale(2, RoundingMode.HALF_UP);
            if (price.compareTo(BigDecimal.ZERO) >= 0) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException | ArithmeticException e) {
            return false;
        }
    }
    public static boolean validateYear(int year){
        return year>1900 && year<2024;
    }

}
