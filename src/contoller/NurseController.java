package contoller;

import model.Nurse;
import model.User;
import java.util.Scanner;

public class NurseController implements AuthorityControllers<Nurse>{

    @Override
    public void viewProfile(Nurse nurse) {

    }

    @Override
    public void updateProfile(Nurse nurse) {

    }

    @Override
    public void start(Nurse currentUser) {
        Scanner scan = new Scanner(System.in);

            System.out.println("Welcome to Sugah Hospital\n\nWhere our first priority is your health,\n     and we spend our blood, sweat and tears achieving it\n\n");
            System.out.println("How shall we help you?\n");

        while (true) {
            System.out.println("\n----- Nurse Dashboard -----");
            System.out.println("1. View Profile");
            System.out.println("2. Update Profile");
            System.out.println("3. View Assigned Wards");
            System.out.println("4. View Appointments");
            System.out.println("5. View Patient Medication Details");
            System.out.println("6. Admit Patient");
            System.out.println("7. Discharge Patient");
            System.out.println("8. View Diagnosis");
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
                    viewAssignedWards(currentUser);
                    break;

                case 4:
                    viewAppointments(currentUser);
                    break;

                case 5:
                    System.out.println("Enter Patient ID:");
                    String patientId = scan.next();
                    viewMedicationDetailsForPatient(currentUser, patientId);
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
    public void viewAppointments(Nurse nurse) {

    }

    @Override
    public void bookAppointments(Nurse nurse) {

    }

    @Override
    public void rescheduleAppointments(Nurse nurse) {

    }

    public void viewAssignedWards(Nurse nurse){

    }

    public void viewMedicationDetailsForPatient(Nurse nurse, String patientId){

    }


    @Override
    public void admitPatient(Nurse nurse) {

    }

    @Override
    public void dischargePatient(Nurse nurse) {

    }

    @Override
    public void viewDiagnosisAndPrescription(Nurse nurse) {

    }

    @Override
    public void viewAdmissions(Nurse nurse) {

    }

}
