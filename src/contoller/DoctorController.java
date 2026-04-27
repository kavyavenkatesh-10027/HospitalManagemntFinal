package contoller;

import model.User;

public class DoctorController implements AuthorityControllers{
    private User currentUser;

    public DoctorController(User user) {
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
    public void viewAppointments(String docId) {
        System.out.println("View appointment ");
    }

    @Override
    public void bookAppointments() {

    }

    @Override
    public void rescheduleAppointments() {

    }

    public boolean takeAppointment(){

    }


    @Override
    public void admitPatient() {

    }

    @Override
    public void dischargePatient() {

    }

    @Override
    public void viewDiagnosis() {

    }

    @Override
    public void viewAdmissions() {

    }

}
