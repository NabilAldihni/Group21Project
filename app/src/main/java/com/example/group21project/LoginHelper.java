package com.example.group21project;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginHelper {

    // Regular expression for validating email
    private static final String EMAIL_PATTERN =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    // Regular expression for validating password
    // Adjust this based on your password policy
    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";

    // Validate email format
    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // Validate password format
    public static boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    // Method to handle user login
    // This is a placeholder method; implement your login logic here
    public static boolean loginUser(String email, String password) {
        // Here, you would add your logic to check the credentials,
        // possibly checking against a database or an authentication service.
        // For now, it's a dummy method that returns true for simplicity.

        return true;
    }
}
