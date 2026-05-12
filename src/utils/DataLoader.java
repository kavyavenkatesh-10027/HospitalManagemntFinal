package utils;

import model.Doctor;
import model.Patient;
import model.Slot;
import repository.DepartmentRepository;
import repository.PatientRepository;
import repository.SlotRepository;
import repository.UserRepo;

import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

public class DataLoader {

    public static void load(){

        // ---------------- PATIENTS ----------------
        Patient p1 = new Patient(
                "Arun Kumar",
                "M",
                LocalDate.of(2000, 5, 12),
                "9876543210",
                "arun@gmail.com",
                Patient.BloodGroup.O_positive,
                "Ravi Kumar",
                "Lakshmi",
                "Suresh",
                "9123456789"
        );

        Patient p2 = new Patient(
                "Priya Sharma",
                "F",
                LocalDate.of(1998, 8, 25),
                "9123456780",
                "priya@gmail.com",
                Patient.BloodGroup.A_positive,
                "Mahesh",
                "Anita",
                "Ramesh",
                "9988776655"
        );

        PatientRepository.addPatient(p1);
        PatientRepository.addPatient(p2);

        UserRepo.saveUser(p1, "arun@123");
        UserRepo.saveUser(p2, "priya@123");

        // ---------------- DEPARTMENTS ----------------
        DepartmentRepository.addDepartment("Cardiology");
        DepartmentRepository.addDepartment("Neurology");
        DepartmentRepository.addDepartment("Geriatrics");

        // ---------------- SLOTS ----------------
        Slot s1 = new Slot("s1", Time.valueOf("10:00:00"), Time.valueOf("11:00:00"), DayOfWeek.MONDAY);
        Slot s2 = new Slot("s2", Time.valueOf("11:00:00"), Time.valueOf("12:00:00"), DayOfWeek.MONDAY);
        Slot s3 = new Slot("s3", Time.valueOf("17:00:00"), Time.valueOf("18:00:00"), DayOfWeek.THURSDAY);

        Slot s4 = new Slot("s4", Time.valueOf("10:00:00"), Time.valueOf("11:00:00"), DayOfWeek.MONDAY);
        Slot s5 = new Slot("s5", Time.valueOf("14:00:00"), Time.valueOf("15:00:00"), DayOfWeek.WEDNESDAY);

        Slot s6 = new Slot("s6", Time.valueOf("08:00:00"), Time.valueOf("09:00:00"), DayOfWeek.TUESDAY);
        Slot s7 = new Slot("s7", Time.valueOf("17:00:00"), Time.valueOf("18:00:00"), DayOfWeek.THURSDAY);

        Slot s8 = new Slot("s8", Time.valueOf("12:00:00"), Time.valueOf("13:00:00"), DayOfWeek.WEDNESDAY);
        Slot s9 = new Slot("s9", Time.valueOf("16:00:00"), Time.valueOf("17:00:00"), DayOfWeek.SATURDAY);

        // ---------------- DOCTOR 1 ----------------
        HashMap<DayOfWeek, List<Slot>> doc1Schedule = new HashMap<>();
        doc1Schedule.put(DayOfWeek.MONDAY, Arrays.asList(s1, s2));
        doc1Schedule.put(DayOfWeek.THURSDAY, Arrays.asList(s3));

        Doctor doc1 = new Doctor(
                "Dr. Rajesh",
                "M",
                LocalDate.of(1980, 3, 15),
                "9876543210",
                "rajesh@hospital.com",
                "dept-1",
                doc1Schedule
        );

        // ---------------- DOCTOR 2 ----------------
        HashMap<DayOfWeek, List<Slot>> doc2Schedule = new HashMap<>();
        doc2Schedule.put(DayOfWeek.MONDAY, Arrays.asList(s4));
        doc2Schedule.put(DayOfWeek.WEDNESDAY, Arrays.asList(s5));

        Doctor doc2 = new Doctor(
                "Dr. Meena",
                "F",
                LocalDate.of(1985, 7, 20),
                "9123456789",
                "meena@hospital.com",
                "dept-2",
                doc2Schedule
        );

        // ---------------- DOCTOR 3 ----------------
        HashMap<DayOfWeek, List<Slot>> doc3Schedule = new HashMap<>();
        doc3Schedule.put(DayOfWeek.TUESDAY, Arrays.asList(s6));
        doc3Schedule.put(DayOfWeek.FRIDAY, Arrays.asList(s7));

        Doctor doc3 = new Doctor(
                "Dr. Karthik",
                "M",
                LocalDate.of(1978, 11, 10),
                "9988776655",
                "karthik@hospital.com",
                "dept-1",
                doc3Schedule
        );

        // ---------------- DOCTOR 4 ----------------
        HashMap<DayOfWeek, List<Slot>> doc4Schedule = new HashMap<>();
        doc4Schedule.put(DayOfWeek.WEDNESDAY, Arrays.asList(s8));
        doc4Schedule.put(DayOfWeek.SATURDAY, Arrays.asList(s9));

        Doctor doc4 = new Doctor(
                "Dr. Anjali",
                "F",
                LocalDate.of(1982, 5, 25),
                "8877665544",
                "anjali@hospital.com",
                "dept-3",
                doc4Schedule
        );

        UserRepo.saveUser(doc1, "rajesh@123");
        UserRepo.saveUser(doc2, "meena@123");
        UserRepo.saveUser(doc3, "karthik@123");
        UserRepo.saveUser(doc4, "anjali@123");

        // ---------------- ADD DOCTORS TO DEPARTMENTS ----------------
        Objects.requireNonNull(DepartmentRepository.findById(doc1.getDepartmentId())).addNewDoctor(doc1);
        Objects.requireNonNull(DepartmentRepository.findById(doc2.getDepartmentId())).addNewDoctor(doc2);
        Objects.requireNonNull(DepartmentRepository.findById(doc3.getDepartmentId())).addNewDoctor(doc3);
        Objects.requireNonNull(DepartmentRepository.findById(doc4.getDepartmentId())).addNewDoctor(doc4);

        // ---------------- SLOT REPOSITORY ----------------
        Map<String, List<Slot>> map = new HashMap<>();
        map.put(doc1.getId(), Arrays.asList(s1, s2, s3));
        map.put(doc2.getId(), Arrays.asList(s4, s5));
        map.put(doc3.getId(), Arrays.asList(s6, s7));
        map.put(doc4.getId(), Arrays.asList(s8, s9));

        SlotRepository.getSlotDoctorMap().putAll(map);

        // ---------------- INITIAL BOOKING COUNT ----------------
        for (Slot s : SlotRepository.getAllSlots()) {
            SlotRepository.getSlotBookingCount().put(s.getSlotId(), 0);
        }
    }
}