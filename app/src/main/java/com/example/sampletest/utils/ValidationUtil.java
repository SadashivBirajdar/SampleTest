package com.example.sampletest.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\"
                    + ".[A-Za-z]{2,})$";
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])" +
            "(?=.*[_A-Za-z])" +
            "(?=\\S+$).{6,30}$";
    private static final String NAME_PATTERN = "^[a-zA-Z0-9'. ]{1,60}$";


    private Pattern pattern;
    private Matcher matcher;

    public boolean validateEmail(final String email) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validatePassword(final String password) {
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public boolean validateName(final String name) {
        pattern = Pattern.compile(NAME_PATTERN);
        matcher = pattern.matcher(name);
        return matcher.matches();
    }

    /**
     * Checks for Null String object
     *
     * @return true for not null and false for null String object
     */
    public boolean isNotNull(String txt) {
        return txt != null && txt.trim().length() > 0;
    }

    public boolean validateNumber(final String number) {
        return number.length() >= 10;
    }

}
