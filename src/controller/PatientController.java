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

    public PatientController(Scanner scan){
        this.scan = scan;
    }

    private final AppointmentService appointmentService = new AppointmentService();


    @Override
    public void viewProfile(Patient currentUser) {
        Patient patient = PatientRepository.findById(currentUser.getId());
        System.out.println(patient);
    }

    @Override
    public void updateProfile(Patient currentUser) {
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

    public void start(Patient currentUser) {

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
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    @Override
    public void viewAppointments(Patient patient) {
        List<Appointment> list = appointmentService.viewAppointmentsByPatientId(patient.getId());
        list.forEach(System.out::println);
    }

    @Override
    public void bookAppointments(Patient patient) {

        // 1. SHOW DEPARTMENTS
        List<Department> departments = DepartmentRepository.getAllDepartments();

        if (departments == null || departments.isEmpty()) {
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

        if (doctors == null || doctors.isEmpty()) {
            System.out.println("No doctors available in this department");
            return;
        }

        // 3. FIND FIRST AVAILABLE DOCTOR AND STORE SLOT:
//        Doctor selectedDoctor = null;
        HashMap<Slot, String> availableSlotsMappingWithDoctorId = new HashMap<>();
        //Slot -> DoctorId

//        outer:
        for (Doctor doc : doctors) {

            HashMap<DayOfWeek, List<Slot>> schedule = doc.getDoctorSchedule();

            if (schedule == null) continue;

            for (List<Slot> slotList : schedule.values()) {

                if (slotList == null) continue;

                for (Slot slot : slotList) {

                    if (slot != null && SlotRepository.isSlotAvailable(slot.getSlotId())) {
//                        selectedDoctor = doc;
                        if (slot != null && SlotRepository.isSlotAvailable(slot.getSlotId())) {
                            if (availableSlotsMappingWithDoctorId.containsKey(slot)) {
                                continue;
                            } else {
                                availableSlotsMappingWithDoctorId.put(slot, doc.getId());
                            }
                        }
                    }
                }
            }
        }

//        if (selectedDoctor == null) {
//            System.out.println("No slots available for this department");
//            return;
//        }
        if (availableSlotsMappingWithDoctorId.isEmpty()) {
            System.out.println("No slots available for this department");
            return;
        }

        // 4. SHOW AVAILABLE SLOTS FOR THAT DOCTOR
        List<Slot> availableSlots = new ArrayList<>(availableSlotsMappingWithDoctorId.keySet());
        int index=1;
        HashMap<Integer, Time> uniqueTimings = new HashMap<>();

        for(Slot slot: availableSlots){
            if(!uniqueTimings.containsValue(slot.getStartTime())) {
                uniqueTimings.put(index, slot.getStartTime());
            }
        }


//        List<Slot> availableSlots = new ArrayList<>();
//
//        HashMap<DayOfWeek, List<Slot>> schedule = selectedDoctor.getDoctorSchedule();
//
//        for (List<Slot> slotList : schedule.values()) {
//
//            if (slotList == null) continue;
//
//            for (Slot slot : slotList) {
//
//                if (slot != null && SlotRepository.isSlotAvailable(slot.getSlotId())) {
//                    availableSlots.add(slot);
//                }
//            }
//        }
//
//        //  IMPORTANT FIX
//        if (availableSlots.isEmpty()) {
//            System.out.println("No available slots for selected doctor");
//            return;
//        }

//        for (int i = 0; i < availableSlots.size(); i++) {
//            System.out.println((i + 1) + ". " + availableSlots.get(i));
//        }
        for (int i = 1; i < index+1; i++) {
            System.out.println(i+". "+uniqueTimings.get(i));
        }


        // test
//        for(Slot s: availableSlots){
//            System.out.println(s);
//        }

        System.out.println("Select Slot:");
        int timeChoice = Integer.parseInt(scan.nextLine());

        if (timeChoice < 1 || timeChoice > index) {
            System.out.println("Invalid slot");
            return;
        }

        Time selectedTime = uniqueTimings.get(timeChoice);

        Slot finalSlot = null;
        for (Slot slot: new ArrayList<>(availableSlotsMappingWithDoctorId.keySet())){
            if(slot.getStartTime().equals(selectedTime)){
                finalSlot = slot;
            }
        }

        String assignedDoctorId;

        //  CRITICAL FIX (prevents your crash)
        if (finalSlot == null) {
            System.out.println("Slot selection failed");
            return;
        }

         assignedDoctorId = availableSlotsMappingWithDoctorId.get(finalSlot);

        if (assignedDoctorId == null) {
            System.out.println("Slot selection failed");
            return;
        }

        if (finalSlot.getSlotId() == null) {
            System.out.println("Invalid slot data");
            return;
        }

        // 5. BOOK SLOT (capacity check)
        if (!SlotRepository.bookSlot(finalSlot.getSlotId())) {
            System.out.println("Slot just got filled. Try again.");
            return;
        }

        // 6. BOOK APPOINTMENT
        appointmentService.bookAppointment(
                patient.getId(),
                selectedDept.getDeptId(),
                assignedDoctorId,
                finalSlot,
                Appointment.STATUS.CONFIRMED
        );

        System.out.println("Appointment booked with Doctor ID: " + assignedDoctorId);
    }

    @Override
    public void rescheduleAppointments(Patient patient) {

        List<Appointment> list = appointmentService.viewAppointmentsByPatientId(patient.getId());

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
                patient.getId(),
                selected.getAppointmentId(),
                newSlot
        );

        System.out.println("Rescheduled successfully");
    }

    public void cancelAppointment(Patient patient) {

        List<Appointment> list = appointmentService.viewAppointmentsByPatientId(patient.getId());

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
                patient.getId(),
                selected.getAppointmentId()
        );

        System.out.println("Appointment cancelled");
    }

    public Consultation viewConsultation(Patient patient) {
        System.out.println("Feature not implemented yet");
        return null;
    }

    public List<Consultation> viewAllConsultation(Patient patient) {
        System.out.println("Feature not implemented yet");
        return null;
    }
}