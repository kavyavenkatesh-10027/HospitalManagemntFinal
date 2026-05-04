package contoller;

import model.User;

public class ReceptionistController implements Controller{
    private User currentUser;

    public ReceptionistController(User user) {
        this.currentUser = user;
    }

    @Override
    public void viewProfile() {

    }

    @Override
    public void updateProfile() {

    }

    @Override
    public void start() {
        System.out.println("Welcome to Sugah Hospital\n\nWhere our first priority is your health,\n     and we spend our blood, sweat and tears achieving it\n\n");
        System.out.println("How shall we help you?\n");
        System.out.println("1. Book Appointment");
        System.out.println("2.View Appointment");
        System.out.println("3. Reschedule Appointment");
        System.out.println("4. Delete Appointment");
        System.out.println("5. View Admission");
        System.out.println("6. View Profile");
        System.out.println("7. Update Profile");
        System.out.println("8. Initialize Billing");
    }

    @Override
    public void viewAppointments(String condition) {

    }

    @Override
    public void bookAppointments() {

    }

    @Override
    public void rescheduleAppointments() {

    }


    public void viewAdmissions(){

    }

    public void triggerBilling(){

    }
}
