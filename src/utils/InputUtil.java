package utils;

import java.util.Scanner;
import java.util.function.Predicate;

public class InputUtil {

    private static final Scanner scan = new Scanner(System.in);

    public static String ask(String prompt) {
        System.out.println(prompt);
        return scan.nextLine().trim();
    }

    public static String askValid(String prompt, String error, Predicate<String> validator) {
        while (true) {
            System.out.println(prompt);
            String input = scan.nextLine().trim();

            if (validator.test(input)) return input;

            System.out.println(error);
        }
    }

    public static String askValidNext(String prompt, String error, Predicate<String> validator) {
        while (true) {
            System.out.println(prompt);
            String input = scan.nextLine().trim();

            if (validator.test(input)) return input;

            System.out.println(error);
        }
    }
}