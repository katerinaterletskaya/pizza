package ru.mail.terletskaya.katerina.controller.validator;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserNameValidator{

    public static List<String> checkUserName(String username, List<String> errors) {
        if (username.trim().length() > 15 || username.trim().length() < 3 ) {
            errors.add("Length of username is not valid. Need les than 15 symbols and more then 3.");
        }
        if (!checkWithRegExp(username.trim())) {
            errors.add("Username is not valid. You only need to enter letters and numbers.");
        }
        return errors;
    }

    public static boolean checkWithRegExp(String userNameString) {
        Pattern p = Pattern.compile("^[a-zA-Z0-9]{3,15}$");
        Matcher m = p.matcher(userNameString);
        return m.matches();
    }
}
