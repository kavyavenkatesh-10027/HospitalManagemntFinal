import contoller.*;
import model.Doctor;
import model.User;
import service.AuthenticationService;
import repository.*;

import java.util.Scanner;

public class Main {

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n------- HOSPITAL MANAGEMENT SYSTEM -------");
            System.out.println("1. Login");
            System.out.println("2. Sign Up");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            String choice = scan.next();

            switch (choice) {
                case "1":
                    User user = AuthenticationService.login();
                    if (user != null) {
                        System.out.println(user);
                        Controller.routeUser(user);
                    }
                    break;

                case "2":
                    User newUser = AuthenticationService.signUp();
                    System.out.println("You can now login.");
                    break;

                case "3":
                    System.out.println("Exiting...");
                    System.exit(0);
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}