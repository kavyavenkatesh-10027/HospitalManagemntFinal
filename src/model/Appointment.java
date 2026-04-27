package model;

import utils.IDGenerator;

public class Appointment {
    private final String appointmentId;
    private final String patientId;
    private final String departmentId;
    private String doctorId;
    private Slot appointmentTiming;
    private STATUS status;
    public enum STATUS {
        CONFIRMED,
        PENDING,
        CANCELLED,
        COMPLETED
    }

    public Appointment(String patientId, String departmentId, String doctorId, Slot appointmentTiming, STATUS status) {
        this.appointmentId = IDGenerator.generateAppointmentId();
        this.patientId = patientId;
        this.departmentId = departmentId;
        this.doctorId = doctorId;
        this.appointmentTiming = appointmentTiming;
        this.status = status;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public Slot getAppointmentTiming() {
        return appointmentTiming;
    }

    public void setAppointmentTiming(Slot appointmentTiming) {
        this.appointmentTiming = appointmentTiming;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId='" + appointmentId + '\'' +
                ", patientId='" + patientId + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", appointmentTiming=" + appointmentTiming +
                ", status=" + status +
                '}';
    }
}
