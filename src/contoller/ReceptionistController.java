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
