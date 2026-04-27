package contoller;

import model.User;

public class NurseController implements AuthorityControllers{

    private User currentUser;

    public NurseController(User user) {
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
    public void viewAppointments(String all) {

    }

    @Override
    public void bookAppointments() {

    }

    @Override
    public void rescheduleAppointments() {

    }

    public void viewAssignedWards(){

    }

    public void viewMedicationDetailsForPatient(String patientId){

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
