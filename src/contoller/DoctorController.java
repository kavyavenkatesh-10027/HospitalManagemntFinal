package contoller;

import model.Doctor;
import model.User;

import java.util.Scanner;

public class DoctorController implements AuthorityControllers<Doctor>{

    @Override
    public void viewProfile(Doctor doctor) {

    }

    @Override
    public void updateProfile(Doctor doctor) {

    }

    @Override
    public void start(Doctor currentUser) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to Sugah Hospital\n\nWhere our first priority is your health,\n     and we spend our blood, sweat and tears achieving it\n\n");
        System.out.println("How shall we help you?\n");

        while (true) {
            System.out.println("\n----- Doctor board -----");
            System.out.println("1. View Profile");
            System.out.println("2. Update Profile");
            System.out.println("3. View Appointments");
            System.out.println("4. Take Appointment");
            System.out.println("5. Reschedule Appointment");
            System.out.println("6. Admit Patient");
            System.out.println("7. Discharge Patient");
            System.out.println("8. View Diagnosis And Prescription");
            System.out.println("9. View Admissions");
            System.out.println("0. Logout");

            int choice = scan.nextInt();

            switch (choice) {
                case 1:
                    viewProfile(currentUser);
                    break;

                case 2:
                    updateProfile(currentUser);
                    break;

                case 3:
                    viewAppointments(currentUser);
                    break;

                case 4:
                    takeAppointment(currentUser);
                    break;

                case 5:
                    rescheduleAppointments(currentUser);
                    break;

                case 6:
                    admitPatient(currentUser);
                    break;

                case 7:
                    dischargePatient(currentUser);
                    break;

                case 8:
                    viewDiagnosisAndPrescription(currentUser);
                    break;

                case 9:
                    viewAdmissions(currentUser);
                    break;

                case 0:
                    System.out.println("Logging out...");
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    @Override
    public void viewAppointments(Doctor doctor) {
        System.out.println("View appointment ");
    }

    @Override
    public void bookAppointments(Doctor doctor) {

    }

    @Override
    public void rescheduleAppointments(Doctor doctor) {

    }

    public boolean takeAppointment(Doctor doctor){
        return true;
    }


    @Override
    public void admitPatient(Doctor doctor) {

    }

    @Override
    public void dischargePatient(Doctor doctor) {

    }

    @Override
    public void viewDiagnosisAndPrescription(Doctor doctor) {

    }

    @Override
    public void viewAdmissions(Doctor doctor) {

    }

}
