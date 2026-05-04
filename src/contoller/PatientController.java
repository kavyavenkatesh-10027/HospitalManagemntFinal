package contoller;

import model.*;
import repository.*;
import service.AppointmentService;
import utils.InputUtil;
import utils.Validator;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PatientController implements Controller {

    private User currentUser;
    private static final Scanner scan = new Scanner(System.in);
    private final AppointmentService appointmentService = new AppointmentService();

    public PatientController(User user) {
        this.currentUser = user;
    }

    @Override
    public void viewProfile() {
        Patient patient = PatientRepository.findById(currentUser.getId());
        System.out.println(patient);
    }

    @Override
    public void updateProfile() {
        Patient patient = PatientRepository.findById(currentUser.getId());

        if(patient == null) {
            return;
        }
        String name = InputUtil.ask("Enter your full name:");
        patient.setName(name);
        String phnNo = InputUtil.askValidNext(
                "Enter your contact no:",
                "Enter a valid contact no:",
                Validator::phnNoValidator
        );
        patient.setPhnNo(phnNo);

        System.out.println("Profile updated");
    }

    @Override
    public void start() {

        System.out.println("Welcome to Sugah Hospital\n\nWhere our first priority is your health,\n     and we spend our blood, sweat and tears achieving it\n\n");
        System.out.println("How shall we help you?\n");

        boolean continueLoop = true;

        while (continueLoop) {
            System.out.println("\n-----PATIENT DASHBOARD-----");
            System.out.println("1. Book Appointment");
            System.out.println("2. View Appointment");
            System.out.println("3. Reschedule Appointment");
            System.out.println("4. Cancel Appointment");
            System.out.println("5. View Profile");
            System.out.println("6. Update Profile");
            System.out.println("7. View Consultation");
            System.out.println("8. View All Visit");
            System.out.println("0. Logout");

            String choice = scan.nextLine();

            switch (choice) {
                case "1":
                    bookAppointments();
                    break;
                case "2":
                    viewAppointments(currentUser.getId());
                    break;
                case "3":
                    rescheduleAppointments();
                    break;
                case "4":
                    cancelAppointment(currentUser.getId());
                    break;
                case "5":
                    viewProfile();
                    break;
                case "6":
                    updateProfile();
                    break;
                case "7":
                    viewConsultation();
                    break;
                case "8":
                    viewAllConsultation();
                    break;
                case "0":
                    continueLoop = false;
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    @Override
    public void viewAppointments(String patId) {
        List<Appointment> list = appointmentService.viewAppointmentsByPatientId(patId);
        list.forEach(System.out::println);
    }

    @Override
    public void bookAppointments() {

        // 1. SHOW DEPARTMENTS
        List<Department> departments = DepartmentRepository.getAllDepartments();

        if (departments.isEmpty()) {
            System.out.println("No departments available");
            return;
        }

        for (int i = 0; i < departments.size(); i++) {
            System.out.println((i + 1) + ". " + departments.get(i).getDeptName());
        }

        System.out.println("Select Department:");
        int deptChoice = Integer.parseInt(scan.nextLine());

        if (deptChoice < 1 || deptChoice > departments.size()) {
            System.out.println("Invalid choice");
            return;
        }

        Department selectedDept = departments.get(deptChoice - 1);

        // 2. GET DOCTORS UNDER DEPARTMENT
        List<Doctor> doctors = selectedDept.getDoctorsUnderDepartment();

        if (doctors.isEmpty()) {
            System.out.println("No doctors available in this department");
            return;
        }

        // 3. FIND FIRST AVAILABLE DOCTOR + SLOT
        Doctor selectedDoctor = null;
        Slot selectedSlot = null;

        outer:
        for (Doctor doc : doctors) {

            HashMap<DayOfWeek, List<Slot>> schedule = doc.getDoctorSchedule();

            for (List<Slot> slotList : schedule.values()) {

                for (Slot slot : slotList) {

                    if (SlotRepository.isSlotAvailable(slot.getSlotId())) {
                        selectedDoctor = doc;
                        selectedSlot = slot;
                        break outer;
                    }
                }
            }
        }

        if (selectedDoctor == null) {
            System.out.println("No slots available for this department");
            return;
        }

        // 4. SHOW AVAILABLE SLOTS FOR THAT DOCTOR
        List<Slot> availableSlots = new ArrayList<>();

        HashMap<DayOfWeek, List<Slot>> schedule = selectedDoctor.getDoctorSchedule();

        for (List<Slot> slotList : schedule.values()) {
            for (Slot slot : slotList) {
                if (SlotRepository.isSlotAvailable(slot.getSlotId())) {
                    availableSlots.add(slot);
                }
            }
        }

        for (int i = 0; i < availableSlots.size(); i++) {
            System.out.println((i + 1) + ". " + availableSlots.get(i));
        }

        System.out.println("Select Slot:");
        int slotChoice = Integer.parseInt(scan.nextLine());

        if (slotChoice < 1 || slotChoice > availableSlots.size()) {
            System.out.println("Invalid slot");
            return;
        }

        Slot finalSlot = availableSlots.get(slotChoice - 1);

        // 5. BOOK SLOT (capacity check)
        if (!SlotRepository.bookSlot(finalSlot.getSlotId())) {
            System.out.println("Slot just got filled. Try again.");
            return;
        }

        // 6. BOOK APPOINTMENT
        appointmentService.bookAppointment(
                currentUser.getId(),
                selectedDept.getDeptId(),
                selectedDoctor.getId(),
                finalSlot,
                Appointment.STATUS.CONFIRMED
        );

        System.out.println("Appointment booked with Doctor ID: " + selectedDoctor.getId());
    }

    @Override
    public void rescheduleAppointments() {

        List<Appointment> list = appointmentService.viewAppointmentsByPatientId(currentUser.getId());

        if (list.isEmpty()) {
            System.out.println("No appointments found");
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }

        System.out.println("Select appointment to reschedule:");
        int choice = Integer.parseInt(scan.nextLine());

        if (choice < 1 || choice > list.size()) {
            System.out.println("Invalid choice");
            return;
        }

        Appointment selected = list.get(choice - 1);

        List<Slot> slots = SlotRepository.getSlotsByDoctorId(selected.getDoctorId());

        for (int i = 0; i < slots.size(); i++) {
            System.out.println((i + 1) + ". " + slots.get(i));
        }

        System.out.println("Select new slot:");
        int slotChoice = Integer.parseInt(scan.nextLine());

        if (slotChoice < 1 || slotChoice > slots.size()) {
            System.out.println("Invalid slot");
            return;
        }

        Slot newSlot = slots.get(slotChoice - 1);

        appointmentService.updateAppointment(
                currentUser.getId(),
                selected.getAppointmentId(),
                newSlot
        );

        System.out.println("Rescheduled successfully");
    }

    public void cancelAppointment(String patientId) {

        List<Appointment> list = appointmentService.viewAppointmentsByPatientId(patientId);

        if (list.isEmpty()) {
            System.out.println("No appointments found");
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }

        System.out.println("Select appointment to cancel:");
        int choice = Integer.parseInt(scan.nextLine());

        if (choice < 1 || choice > list.size()) {
            System.out.println("Invalid choice");
            return;
        }

        Appointment selected = list.get(choice - 1);

        appointmentService.deleteAppointment(
                patientId,
                selected.getAppointmentId()
        );

        System.out.println("Appointment cancelled");
    }

    public Consultation viewConsultation() {
        System.out.println("Feature not implemented yet");
        return null;
    }

    public List<Consultation> viewAllConsultation() {
        System.out.println("Feature not implemented yet");
        return null;
    }
}