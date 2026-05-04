package contoller;

import model.User;

import java.util.Scanner;

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
                    viewProfile();
                    break;

                case 2:
                    updateProfile();
                    break;

                case 3:
                    viewAppointments("");
                    break;

                case 4:
                    takeAppointment();
                    break;

                case 5:
                    rescheduleAppointments();
                    break;

                case 6:
                    admitPatient();
                    break;

                case 7:
                    dischargePatient();
                    break;

                case 8:
                    viewDiagnosisAndPrescription();
                    break;

                case 9:
                    viewAdmissions();
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
        return true;
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
