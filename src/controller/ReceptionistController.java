package controller;

import model.Receptionist;
import java.util.Scanner;

public class ReceptionistController implements Controller<Receptionist> {

    private static final Scanner scan = new Scanner(System.in);

    @Override
    public void viewProfile(Receptionist receptionist) {

    }

    @Override
    public void updateProfile(Receptionist receptionist) {

    }

    @Override
    public void start(Receptionist currentUser) {

        System.out.println("Welcome to Sugah Hospital\n\nWhere our first priority is your health,\n     and we spend our blood, sweat and tears achieving it\n\n");
        System.out.println("How shall we help you?\n");

        boolean continueLoop = true;

        while (continueLoop) {
            System.out.println("\n-----RECEPTIONIST DASHBOARD-----");
            System.out.println("1. Book Appointment");
            System.out.println("2. View Appointment");
            System.out.println("3. Reschedule Appointment");
            System.out.println("4. Delete Appointment");
            System.out.println("5. View Admission");
            System.out.println("6. View Profile");
            System.out.println("7. Update Profile");
            System.out.println("8. Initialize Billing");
            System.out.println("0. Logout");

            String choice = scan.nextLine();

            switch (choice) {
                case "1":
                    bookAppointments(currentUser);
                    break;
                case "2":
                    viewAppointments(currentUser);
                    break;
                case "3":
                    rescheduleAppointments(currentUser);
                    break;
                case "4":
                    deleteAppointment(currentUser);
                    break;
                case "5":
                    viewAdmissions(currentUser);
                    break;
                case "6":
                    viewProfile(currentUser);
                    break;
                case "7":
                    updateProfile(currentUser);
                    break;
                case "8":
                    triggerBilling(currentUser);
                    break;
                case "0":
                    continueLoop = false;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    @Override
    public void viewAppointments(Receptionist receptionist) {

    }

    @Override
    public void bookAppointments(Receptionist receptionist) {

    }

    @Override
    public void rescheduleAppointments(Receptionist receptionist) {

    }

    public void deleteAppointment(Receptionist receptionist) {

    }

    public void viewAdmissions(Receptionist receptionist) {

    }

    public void triggerBilling(Receptionist receptionist) {

    }
}