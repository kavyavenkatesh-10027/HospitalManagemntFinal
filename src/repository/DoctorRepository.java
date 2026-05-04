package repository;

import contoller.DoctorController;
import model.Doctor;
import model.Patient;
import model.Slot;

import javax.print.Doc;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DoctorRepository {

    private static List<Doctor> allDoctors = new ArrayList<>();

    static {

    }

    public static List<Doctor> getAllDoctors() {
        return new ArrayList<>(allDoctors);
    }

    public static Doctor findById(String doctorId) {
        for (Doctor d : allDoctors) {
            if (d.getId().equals(doctorId)) {
                return d;
            }
        }
        return null;
    }

    public static Doctor findByEmail(String email) {
        for (Doctor d : allDoctors) {
            if (d.getEmailId().equals(email)) {
                return d;
            }
        }
        return null;
    }



    public static List<Slot> getScheduleByDoctorId(String doctorId){
        //returning null for the time being
        return null;
    }

    public static List<Doctor> getAvailableDoctors(Time time){
        //returning null for the time being
        return null;
    }

    public static void addDoctor(Doctor doctor){
        allDoctors.add(doctor);
    }

    public static void deleteDoctor(String doctorId){
        allDoctors.removeIf(d -> d.getId().equals(doctorId));
    }

    public static void editDoctor(String doctorId, Doctor updatedDoctor){
        for (int i = 0; i < allDoctors.size(); i++) {
            if (allDoctors.get(i).getId().equals(doctorId)) {
                allDoctors.set(i, updatedDoctor);
                return;
            }
        }
    }
}