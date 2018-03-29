package ru.mail.terletskaya.katerina.controller.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberCardValidator {
    public boolean validateNumberCard(String numberCard) {
        Pattern p = Pattern.compile("[0-9]{4} [0-9]{4} [0-9]{4} [0-9]{4}");
        Matcher m = p.matcher(numberCard.trim());
        return m.matches();
    }
}
