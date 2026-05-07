package controller;

import model.*;

import java.time.DayOfWeek;
import java.util.Scanner;

public class AdminController implements AuthorityControllers<Admin>{

    private static final Scanner scan = new Scanner(System.in);

    @Override
    public void viewProfile(Admin admin) {

    }

    @Override
    public void updateProfile(Admin admin) {

    }

    @Override
    public void start(Admin currentUser) {
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
                    deleteAppointments(currentUser);
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
    public void viewAppointments(Admin admin) {

    }

    private void viewAllAppointments(){}

    private void viewAppointmentByAppointmentByAppointmentId(String appointmentId){}

    private void viewAppointmentByStatus(Appointment.STATUS status){}

    private void viewAppointmentForPatientId(String patientId){}

    private void viewAppointmentForDoctorId(String doctorId){}

    private void viewAppointmentsUnderDepartmentByDepartmentId(String deptId){}

    private void viewAppointmentScheduleOnDay(DayOfWeek day){}

    private void viewAppointmentBySlotId(String slot){}



    @Override
    public void bookAppointments(Admin admin) {

    }

    @Override
    public void rescheduleAppointments(Admin admin) {

    }

    public void deleteAppointments(Admin admin){

    }

    public void viewAllDoctors(Admin admin){

    }

    public void viewAllNurses(Admin admin){

    }

    public void viewAllPatients(Admin admin){

    }

    public void viewAllReceptionists(Admin admin){

    }

    public void viewAllUsers(Admin admin){

    }
    public void addUser(Admin admin){

    }

    public void addDoctor(Admin admin){

    }

    public void addNurse(Admin admin){

    }

    public void addPatient(Admin admin){

    }

    public void addReceptionist(Admin admin){

    }

    public void deleteUser(Admin admin){

    }

    public void deleteDoctor(Admin admin){

    }

    public void deleteNurse(Admin admin){

    }

    public void deletePatient(Admin admin){

    }

    public void deleteReceptionist(Admin admin){

    }

    public void editUser(Admin admin){

    }

    public void editDoctor(Admin admin){

    }

    public void editNurse(Admin admin){

    }

    public void editPatient(Admin admin){

    }

    public void editReceptionist(Admin admin){

    }


    @Override
    public void admitPatient(Admin admin) {

    }

    @Override
    public void dischargePatient(Admin admin) {

    }

    @Override
    public void viewDiagnosisAndPrescription(Admin admin) {

    }

    @Override
    public void viewAdmissions(Admin admin) {

    }

}
