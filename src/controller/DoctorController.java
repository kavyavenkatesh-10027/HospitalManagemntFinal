package controller;

import model.Appointment;
import model.Department;
import model.Doctor;
import model.Slot;
import repository.DepartmentRepository;
import repository.PatientRepository;
import repository.SlotRepository;
import service.AppointmentService;

import java.util.*;

public class DoctorController implements AuthorityControllers<Doctor>{
    private final Scanner scan;
    private final AppointmentService appointmentService = new AppointmentService();

    public DoctorController(Scanner scan){
        this.scan = scan;
    }

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
            System.out.println("4. Book Appointment");
            System.out.println("5. Take Appointment");
            System.out.println("6. Reschedule Appointment");
            System.out.println("7. Admit Patient");
            System.out.println("8. Discharge Patient");
            System.out.println("9. View Diagnosis And Prescription");
            System.out.println("10. View Admissions");
            System.out.println("11. View My Schedule");
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
                    bookAppointments(currentUser);
                    break;

                case 5:
                    takeAppointment(currentUser);
                    break;

                case 6:
                    rescheduleAppointments(currentUser);
                    break;

                case 7:
                    admitPatient(currentUser);
                    break;

                case 8:
                    dischargePatient(currentUser);
                    break;

                case 9:
                    viewDiagnosisAndPrescription(currentUser);
                    break;

                case 10:
                    viewAdmissions(currentUser);
                    break;

                case 11:
                    viewDoctorSchedule(currentUser);
                    return;

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
        if (doctor == null) {
            System.out.println("Invalid doctor");
            return;
        }

        List<Appointment> appointments =
                appointmentService.viewAppointmentsByDoctorId(doctor.getId());

        if (appointments == null || appointments.isEmpty()) {
            System.out.println("No appointments found");
            return;
        }

        for (int i = 0; i < appointments.size(); i++) {
            System.out.println(i+1+". "+appointments.get(i));
        }
    }

    @Override
    public void bookAppointments(Doctor doctor) {

        if (doctor == null) {
            System.out.println("Invalid doctor");
            return;
        }

        Department selectedDept = DepartmentRepository.findById(doctor.getDepartmentId());

        System.out.println("Enter patient's Id");
        String patientId = scan.next();

        if(PatientRepository.findById(patientId)==null){
            System.out.println("Invalid patient Id. Patient doesn't exist.");
            return;
        }

        // GET DOCTORS
        List<Doctor> doctorsUnderDepartment = selectedDept.getDoctorsUnderDepartment();

        Doctor selectedDoctor;

        if (doctorsUnderDepartment == null || doctorsUnderDepartment.isEmpty()) {
            System.out.println("No doctorsUnderDepartment available in this department");
            return;
        }

        System.out.println("Select doctor to allot appointment to:");
        for (int i=0; i<doctorsUnderDepartment.size(); i++){
            System.out.println(i+1+". "+doctorsUnderDepartment.get(i+1).getName());
        }

        int choice;

        try {
            choice = scan.nextInt();
        }catch (TypeNotPresentException tnpe){
            System.out.println("Invalid choice");
            return;
        }

        if(choice>doctorsUnderDepartment.size() || choice<=0){
            System.out.println("Invalid choice");
            return;
        }

        selectedDoctor = doctorsUnderDepartment.get(choice);

        //SELECTING SLOT AFTER ANALYZING DOCTOR SCHEDULE
        List<Slot> doctorSchedule = SlotRepository.getAvailableSlotsByDoctorId(selectedDoctor.getId());

        for (int i=0; i<doctorSchedule.size(); i++){
            System.out.println(i+1+". "+doctorSchedule.get(i));
        }

        int scheduleChoice;

        try {
            scheduleChoice = scan.nextInt();
        }catch (TypeNotPresentException tnpe){
            System.out.println("Invalid choice");
            return;
        }

        if(choice>doctorsUnderDepartment.size() || scheduleChoice<=0){
            System.out.println("Invalid choice");
            return;
        }

        Slot selectedTime = doctorSchedule.get(scheduleChoice);

        appointmentService.bookAppointment(
                patientId,
                selectedDept.getDeptId(),
                selectedDoctor.getId(),
                selectedTime,
                Appointment.STATUS.CONFIRMED
        );

        System.out.println("Appointment booked successfully");
        System.out.println("Appointment taken byDoctor : " + selectedDoctor.getName());

    }

    @Override
    public void rescheduleAppointments(Doctor doctor) {
        viewAppointments(doctor);

    }

    private void takeAppointment(Doctor doctor){
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

    private void viewDoctorSchedule(Doctor currentUser) {

    }

}
