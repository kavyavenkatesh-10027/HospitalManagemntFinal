package repository;

import model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PatientRepository {

    private final static List<Patient> allPatients = new ArrayList<>();


    public static List<Patient> getAllPatients() {
        return new ArrayList<>(allPatients);
    }

    public static Patient findById(String patientId) {
        for (Patient p : allPatients) {
            if (p.getId().equals(patientId)) {
                return p;
            }
        }
        return null;
    }

    public static Patient findByEmail(String email) {
        for (Patient p : allPatients) {
            if (p.getEmailId().equals(email)) {
                return p;
            }
        }
        return null;
    }

    public static List<Appointment> viewPatientAppointments(String patientId){
        Patient patient = findById(patientId);
        return patient != null ? patient.getPatientAppointments() : new ArrayList<>();
    }

    public static List<Consultation> viewPatientHistory(String patientId){
        Patient patient = findById(patientId);
        return patient != null ? patient.getPatientRecord() : new ArrayList<>();
    }

    public static void addPatient(Patient patient){
        allPatients.add(patient);
    }

    public static void deletePatient(String patientId){
        allPatients.removeIf(p -> p.getId().equals(patientId));
    }

    public static void editPatient(String patientId, Patient updatedPatient){
        for (int i = 0; i < allPatients.size(); i++) {
            if (allPatients.get(i).getId().equals(patientId)) {
                allPatients.set(i, updatedPatient);
                return;
            }
        }
    }
}