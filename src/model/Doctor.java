package model;

import utils.IDGenerator;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Doctor extends User {
    private final String doctorId;
    private String departmentId;
    private HashMap<DayOfWeek, List<Slot>> doctorSchedule;
    private final List<Appointment> assignedAppointments = new ArrayList<>();

    public Doctor(String name, String gender, LocalDate dob, String phnNo, String emailId, String departmentId, HashMap<DayOfWeek, List<Slot>> doctorSchedule) {
        super(name, gender, dob, phnNo, emailId);
        this.doctorId = IDGenerator.generateDoctorId();
        this.departmentId = departmentId;
        this.doctorSchedule = doctorSchedule;
    }

    @Override
    public String getId() {
        return doctorId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public List<Appointment> getAssignedAppointments() {
        return assignedAppointments;
    }

    public HashMap<DayOfWeek, List<Slot>> getDoctorSchedule() {
        return doctorSchedule;
    }

    public void setDoctorSchedule(HashMap<DayOfWeek, List<Slot>> doctorSchedule) {
        this.doctorSchedule = doctorSchedule;
    }

    public boolean addAppointment(Appointment newAppointment) {
        assignedAppointments.add(newAppointment);
        return true;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId='" + doctorId + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", doctorSchedule=" + doctorSchedule +
                ", assignedAppointments=" + assignedAppointments +
                ", joinedAt=" + joinedAt +
                '}';
    }
}
