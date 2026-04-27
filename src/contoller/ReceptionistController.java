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
        System.out.println("How shall we help you?\n\n1. Book Appointment\n2.View Appointment\n 3. Reschedule Appointment\n 4. Delete Appointment\n 5. View Admission\n 6. View Profile\n 7. Update Profile\n 8. Initialize Billing");    }

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
