package contoller;

import model.*;
import repository.AppointmentRepository;

public class AdminController implements AuthorityControllers{
    private User currentUser;

    public AdminController(User user) {
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
    public void viewAppointments(String temp) {

    }

    @Override
    public void bookAppointments() {

    }

    @Override
    public void rescheduleAppointments() {

    }

    public void deleteAppointments(){

    }

    public void viewAllDoctors(){

    }

    public void viewAllNurses(){

    }

    public void viewAllPatients(){

    }

    public void viewAllReceptionists(){

    }

    public void viewAllUsers(){

    }
    public void addUser(){

    }

    public void addDoctor(){

    }

    public void addNurse(){

    }

    public void addPatient(){

    }

    public void addReceptionist(){

    }

    public void deleteUser(){

    }

    public void deleteDoctor(){

    }

    public void deleteNurse(){

    }

    public void deletePatient(){

    }

    public void deleteReceptionist(){

    }

    public void editUser(){

    }

    public void editDoctor(){

    }

    public void editNurse(){

    }

    public void editPatient(){

    }

    public void editReceptionist(){

    }


    @Override
    public void admitPatient() {

    }

    @Override
    public void dischargePatient() {

    }

    @Override
    public void viewDiagnosisAndPrescription() {

    }

    @Override
    public void viewAdmissions() {

    }

}
