package utils;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class Validator {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    private static final Pattern PHONE_PATTERN =
            Pattern.compile("^[1-9][0-9]{9}$");

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[^A-Za-z\\d]).{6,}$");

    private static final Pattern GENDER_PATTERN =
            Pattern.compile("^[MFO]$", Pattern.CASE_INSENSITIVE);

    private static final Pattern BOOLEAN_PATTERN =
            Pattern.compile("^(true|false|t|f)$", Pattern.CASE_INSENSITIVE);

    private static final Pattern NOT_EMPTY_PATTERN =
            Pattern.compile("^\\S.*$");


    public static boolean emailValidator(String email) {
        if (email == null) return false;
        return EMAIL_PATTERN.matcher(email.trim()).matches();
    }

    public static boolean phnNoValidator(String mobile) {
        if (mobile == null) return false;
        return PHONE_PATTERN.matcher(mobile.trim()).matches();
    }

    public static boolean passwordValidator(String pswd) {
        if (pswd == null) return false;

        boolean valid = PASSWORD_PATTERN.matcher(pswd.trim()).matches();

        if (!valid) {
            System.out.println("Password must be at least 6 chars with letter, number, and special character");
        }

        return valid;
    }

    public static boolean dobValidator(LocalDate dob) {
        return dob != null && dob.isBefore(LocalDate.now());
    }

    public static boolean genderValidator(String gender) {
        if (gender == null) return false;
        return GENDER_PATTERN.matcher(gender.trim()).matches();
    }

    public static boolean notNullValidator(String str) {
        if (str == null) return false;
        return NOT_EMPTY_PATTERN.matcher(str.trim()).matches();
    }

    public static boolean isBoolean(String input) {
        if (input == null) return false;
        return BOOLEAN_PATTERN.matcher(input.trim()).matches();
    }
}