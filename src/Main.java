import contoller.*;
import model.User;
import service.AuthenticationService;
import repository.*;

import javax.management.relation.Role;
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

            int choice = scan.nextInt();

            switch (choice) {
                case 1:
                    User user = AuthenticationService.login();
                    if (user != null) {
                        routeUser(user);
                    }
                    break;

                case 2:
                    User newUser = AuthenticationService.signUp();
                    System.out.println("You can now login.");
                    break;

                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    public static void routeUser(User user) {
        User.Role role = user.getRole();
        switch (role) {
            case PATIENT:
                PatientController pc = new PatientController(user);
                pc.start();
                break;

            case DOCTOR:
                DoctorController dc = new DoctorController(user);
                dc.start();
                break;

            case NURSE:
                NurseController nc = new NurseController(user);
                nc.start();
                break;

            case RECEPTIONIST:
                ReceptionistController rc = new ReceptionistController(user);
                rc.start();
                break;
        }
    }

}