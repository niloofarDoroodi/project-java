package org.example.util;

public class Validation {
    public static boolean validEmail(String email){
        String regex = "^(?i)[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$";
        return email.matches(regex);

    }

    public static boolean isWebsite(String website) {
        String regex = "^(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})([/\\w .-]*)*/?$";
        return website.matches(regex);
    }
    public static boolean validPassword(String password){
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,10}$");

    }

    public static boolean validPhoneNumber(String phoneNumber){
        return phoneNumber.matches("\"^(\\\\+\\\\d{1,3})?([\\\\s\\\\-\\\\.\\\\(\\\\)]*\\\\d){10,14}$\";");
    }
    public static boolean validNationalCode(String nationalCode){
        return nationalCode.matches("^(\\d{10})$");
    }
}
