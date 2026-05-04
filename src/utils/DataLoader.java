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

        DepartmentRepository.addDepartment("Cardiology");
        DepartmentRepository.addDepartment("Neurology");
        DepartmentRepository.addDepartment("Geriatrics");


        // ---------------- SLOTS ----------------
        Slot s1 = new Slot("s1", Time.valueOf("10:00:00"), Time.valueOf("11:00:00"), DayOfWeek.MONDAY);
        Slot s2 = new Slot("s2", Time.valueOf("11:00:00"), Time.valueOf("12:00:00"), DayOfWeek.MONDAY);
        Slot s3 = new Slot("s3", Time.valueOf("17:00:00"), Time.valueOf("18:00:00"), DayOfWeek.THURSDAY);

        Slot s4 = new Slot("s4", Time.valueOf("09:00:00"), Time.valueOf("10:00:00"), DayOfWeek.MONDAY);
        Slot s5 = new Slot("s5", Time.valueOf("14:00:00"), Time.valueOf("15:00:00"), DayOfWeek.WEDNESDAY);

        // ---------------- DOCTOR 1 SCHEDULE ----------------
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

        Objects.requireNonNull(DepartmentRepository.findById(doc1.getDepartmentId())).addNewDoctor(doc1);

        // ---------------- DOCTOR 2 SCHEDULE ----------------
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

        Objects.requireNonNull(DepartmentRepository.findById(doc2.getDepartmentId())).addNewDoctor(doc2);


        // ---------------- LINK TO SLOT REPOSITORY ----------------
        Map<String, List<Slot>> map = new HashMap<>();
        map.put(doc1.getId(), Arrays.asList(s1, s2, s3));
        map.put(doc2.getId(), Arrays.asList(s4, s5));

        // Reflection of internal map (since you didn't expose setter)
        SlotRepository.getSlotDoctorMap().putAll(map);

        // Initialize booking count = 0
        for (Slot s : SlotRepository.getAllSlots()) {
            SlotRepository.getSlotBookingCount().put(s.getSlotId(), 0);
        }
    }
}
