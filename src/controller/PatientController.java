package controller;

import model.*;
import repository.*;
import service.AppointmentService;
import utils.InputUtil;
import utils.Validator;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.*;

public class PatientController implements Controller<Patient> {

    private final Scanner scan;
    private final AppointmentService appointmentService;

    public PatientController(Scanner scan) {
        this.scan = scan;
        this.appointmentService = new AppointmentService();
    }

    @Override
    public void viewProfile(Patient currentUser) {

        if (currentUser == null) {
            System.out.println("Invalid patient");
            return;
        }

        Patient patient = PatientRepository.findById(currentUser.getId());

        if (patient == null) {
            System.out.println("Patient not found");
            return;
        }

        System.out.println(patient);
    }

    @Override
    public void updateProfile(Patient currentUser) {

        if (currentUser == null) {
            System.out.println("Invalid patient");
            return;
        }

        Patient patient = PatientRepository.findById(currentUser.getId());

        if (patient == null) {
            System.out.println("Patient not found");
            return;
        }

        String name = InputUtil.ask("Enter your full name:").trim();

        String phnNo = InputUtil.askValidNext(
                "Enter your contact no:",
                "Enter a valid contact no:",
                Validator::phnNoValidator
        ).trim();

        patient.setName(name);
        patient.setPhnNo(phnNo);

        System.out.println("Profile updated successfully");
    }

    public void start(Patient currentUser) {

        if (currentUser == null) {
            System.out.println("Invalid login");
            return;
        }

        System.out.println("Welcome to Sugah Hospital\n\nWhere our first priority is your health, \nand we spend our blood, sweat and tears achieving it");

        boolean continueLoop = true;

        while (continueLoop) {

            System.out.println("\n----- PATIENT DASHBOARD -----");
            System.out.println("1. Book Appointment");
            System.out.println("2. View Appointment");
            System.out.println("3. Reschedule Appointment");
            System.out.println("4. Cancel Appointment");
            System.out.println("5. View Profile");
            System.out.println("6. Update Profile");
            System.out.println("7. View Consultation");
            System.out.println("8. View All Visit");
            System.out.println("0. Logout");

            String choice = scan.nextLine().trim();

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
                    cancelAppointment(currentUser);
                    break;

                case "5":
                    viewProfile(currentUser);
                    break;

                case "6":
                    updateProfile(currentUser);
                    break;

                case "7":
                    viewConsultation(currentUser);
                    break;

                case "8":
                    viewAllConsultation(currentUser);
                    break;

                case "0":
                    continueLoop = false;
                    System.out.println("Logged out successfully");
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    @Override
    public void viewAppointments(Patient patient) {

        if (patient == null) {
            System.out.println("Invalid patient");
            return;
        }

        List<Appointment> appointments =
                appointmentService.viewAppointmentsByPatientId(patient.getId());

        if (appointments == null || appointments.isEmpty()) {
            System.out.println("No appointments found");
            return;
        }

        appointments.forEach(System.out::println);
    }

    @Override
    public void bookAppointments(Patient patient) {

        if (patient == null) {
            System.out.println("Invalid patient");
            return;
        }

        // SHOW DEPARTMENTS
        List<Department> departments = DepartmentRepository.getAllDepartments();

        if (departments == null || departments.isEmpty()) {
            System.out.println("No departments available");
            return;
        }

        System.out.println("\n----- DEPARTMENTS -----");

        for (int i = 0; i < departments.size(); i++) {
            System.out.println((i + 1) + ". " + departments.get(i).getDeptName());
        }

        int deptChoice;

        try {
            System.out.println("Select Department:");
            deptChoice = Integer.parseInt(scan.nextLine().trim());

        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
            return;
        }

        if (deptChoice < 1 || deptChoice > departments.size()) {
            System.out.println("Invalid department choice");
            return;
        }

        Department selectedDept = departments.get(deptChoice - 1);

        // GET DOCTORS
        List<Doctor> doctors = selectedDept.getDoctorsUnderDepartment();

        if (doctors == null || doctors.isEmpty()) {
            System.out.println("No doctors available in this department");
            return;
        }

        // SLOT -> DOCTOR_ID
        Map<Slot, String> availableSlotsMap = new LinkedHashMap<>();

        for (Doctor doctor : doctors) {

            if (doctor == null) {
                continue;
            }

            HashMap<DayOfWeek, List<Slot>> schedule =
                    doctor.getDoctorSchedule();

            if (schedule == null || schedule.isEmpty()) {
                continue;
            }

            for (List<Slot> slotList : schedule.values()) {

                if (slotList == null || slotList.isEmpty()) {
                    continue;
                }

                for (Slot slot : slotList) {

                    if (slot == null || slot.getSlotId() == null) {
                        continue;
                    }

                    boolean available =
                            SlotRepository.isSlotAvailable(slot.getSlotId());

                    if (!available) {
                        continue;
                    }

                    availableSlotsMap.putIfAbsent(slot, doctor.getId());
                }
            }
        }

        if (availableSlotsMap.isEmpty()) {
            System.out.println("No slots available");
            return;
        }

        // UNIQUE TIMINGS
        List<Slot> availableSlots =
                new ArrayList<>(availableSlotsMap.keySet());

        Map<Integer, Time> uniqueTimings = new LinkedHashMap<>();

        int index = 1;

        for (Slot slot : availableSlots) {

            if (slot == null || slot.getStartTime() == null) {
                continue;
            }

            if (!uniqueTimings.containsValue(slot.getStartTime())) {

                uniqueTimings.put(index, slot.getStartTime());
                index++;
            }
        }

        if (uniqueTimings.isEmpty()) {
            System.out.println("No valid slot timings found");
            return;
        }

        System.out.println("\n----- AVAILABLE SLOTS -----");

        for (Map.Entry<Integer, Time> entry : uniqueTimings.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }

        int timeChoice;

        try {
            System.out.println("Select Slot:");
            timeChoice = Integer.parseInt(scan.nextLine().trim());

        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
            return;
        }

        if (!uniqueTimings.containsKey(timeChoice)) {
            System.out.println("Invalid slot choice");
            return;
        }

        Time selectedTime = uniqueTimings.get(timeChoice);

        Slot finalSlot = null;

        for (Slot slot : availableSlotsMap.keySet()) {

            if (slot == null || slot.getStartTime() == null) {
                continue;
            }

            if (slot.getStartTime().equals(selectedTime)) {
                finalSlot = slot;
                break;
            }
        }

        if (finalSlot == null) {
            System.out.println("Slot selection failed");
            return;
        }

        String assignedDoctorId = availableSlotsMap.get(finalSlot);

        if (assignedDoctorId == null) {
            System.out.println("Doctor assignment failed");
            return;
        }

        if (!SlotRepository.bookSlot(finalSlot.getSlotId())) {
            System.out.println("Slot just got filled. Try again.");
            return;
        }

        appointmentService.bookAppointment(
                patient.getId(),
                selectedDept.getDeptId(),
                assignedDoctorId,
                finalSlot,
                Appointment.STATUS.CONFIRMED
        );

        System.out.println("Appointment booked successfully");
        System.out.println("Doctor ID: " + assignedDoctorId);
    }

    @Override
    public void rescheduleAppointments(Patient patient) {

        if (patient == null) {
            System.out.println("Invalid patient");
            return;
        }

        List<Appointment> appointments =
                appointmentService.viewAppointmentsByPatientId(patient.getId());

        if (appointments == null || appointments.isEmpty()) {
            System.out.println("No appointments found");
            return;
        }

        System.out.println("\n----- APPOINTMENTS -----");

        for (int i = 0; i < appointments.size(); i++) {
            System.out.println((i + 1) + ". " + appointments.get(i));
        }

        int appointmentChoice;

        try {
            System.out.println("Select appointment to reschedule:");
            appointmentChoice = Integer.parseInt(scan.nextLine().trim());

        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
            return;
        }

        if (appointmentChoice < 1 ||
                appointmentChoice > appointments.size()) {

            System.out.println("Invalid appointment choice");
            return;
        }

        Appointment selectedAppointment =
                appointments.get(appointmentChoice - 1);

        List<Slot> slots =
                SlotRepository.getSlotsByDoctorId(
                        selectedAppointment.getDoctorId()
                );

        if (slots == null || slots.isEmpty()) {
            System.out.println("No slots available");
            return;
        }

        System.out.println("\n----- AVAILABLE SLOTS -----");

        for (int i = 0; i < slots.size(); i++) {
            System.out.println((i + 1) + ". " + slots.get(i));
        }

        int slotChoice;

        try {
            System.out.println("Select new slot:");
            slotChoice = Integer.parseInt(scan.nextLine().trim());

        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
            return;
        }

        if (slotChoice < 1 || slotChoice > slots.size()) {
            System.out.println("Invalid slot");
            return;
        }

        Slot newSlot = slots.get(slotChoice - 1);

        appointmentService.updateAppointment(
                patient.getId(),
                selectedAppointment.getAppointmentId(),
                newSlot
        );

        System.out.println("Appointment rescheduled successfully");
    }

    public void cancelAppointment(Patient patient) {

        if (patient == null) {
            System.out.println("Invalid patient");
            return;
        }

        List<Appointment> appointments =
                appointmentService.viewAppointmentsByPatientId(patient.getId());

        if (appointments == null || appointments.isEmpty()) {
            System.out.println("No appointments found");
            return;
        }

        System.out.println("\n----- APPOINTMENTS -----");

        for (int i = 0; i < appointments.size(); i++) {
            System.out.println((i + 1) + ". " + appointments.get(i));
        }

        int choice;

        System.out.println("Select appointment to cancel");

        if(scan.hasNextInt()) {
            choice=scan.nextInt();
        }else{
            throw new NumberFormatException("Invalid appointment number");
        }
//        try {
//            System.out.println("Select appointment to cancel:");
//            choice = Integer.parseInt(scan.nextLine().trim());
//
//        } catch (NumberFormatException e) {
//            System.out.println("Invalid input");
//            return;
//        }

        if (choice < 1 || choice > appointments.size()) {
            System.out.println("Invalid choice");
            return;
        }

        Appointment selectedAppointment = appointments.get(choice - 1);

        appointmentService.deleteAppointment(
                patient.getId(),
                selectedAppointment.getAppointmentId()
        );

        System.out.println("Appointment cancelled successfully");
    }

    public Consultation viewConsultation(Patient patient) {

        if (patient == null) {
            System.out.println("Invalid patient");
            return null;
        }

        System.out.println("Feature not implemented yet");
        return null;
    }

    public List<Consultation> viewAllConsultation(Patient patient) {

        if (patient == null) {
            System.out.println("Invalid patient");
            return null;
        }

        System.out.println("Feature not implemented yet");
        return null;
    }
}