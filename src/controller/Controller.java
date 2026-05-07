package controller;

import model.Slot;
import model.User;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public interface Controller<T extends User>{
    public static void routeUser(User user) {
        User.Role role = user.getRole();
        System.out.println(role);
        Controller controller = null;
        switch (role) {
            case PATIENT:
                controller = new PatientController();
                break;

            case DOCTOR:
                controller = new DoctorController();
                break;

            case NURSE:
                controller = new NurseController();
                break;

            case RECEPTIONIST:
                controller = new ReceptionistController();
                break;

        }
        assert controller != null;
        controller.start(user);
    }
    public abstract void viewProfile(T currentUser);

    public abstract void updateProfile(T currentUser);

    public abstract void start(T currentUser);

    public abstract void viewAppointments(T currentUser);

    public abstract void bookAppointments(T currentUser);

    public static Slot selectSlot(HashMap<DayOfWeek, List<Slot>> doctorSchedule, Scanner scan) {

        List<Slot> allSlots = new ArrayList<>();

        int option = 1;

        for (DayOfWeek day : DayOfWeek.values()) {

            List<Slot> slots = doctorSchedule.get(day);

            if (slots == null || slots.isEmpty()) continue;

            System.out.println("\n--- " + day + " ---");

            for (Slot slot : slots) {
                System.out.println(option + ". " + slot.getStartTime() + " - " + slot.getEndTime());
                allSlots.add(slot);
                option++;
            }
        }

        if (allSlots.isEmpty()) {
            System.out.println("No slots available");
            return null;
        }

        System.out.print("\nSelect option: ");
        int choice = scan.nextInt();

        if (choice < 1 || choice > allSlots.size()) {
            System.out.println("Invalid choice");
            return null;
        }

        return allSlots.get(choice - 1);
    }

    public abstract void rescheduleAppointments(T currentUser);

    public default void logout(T currentUser){

    }
}
