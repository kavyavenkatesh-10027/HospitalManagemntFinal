package model;

import java.time.LocalDate;

public class Consultation {
    private final String appointmentId;
    private final LocalDate consultationTime;
    private String diagnosis;
    private String prescription;
    private final double consultationFee;
    private boolean admissionNeeded;

    public Consultation(String appointmentId, LocalDate consultationTime, String diagnosis, String prescription, double consultationFee, boolean admissionNeeded) {
        this.appointmentId = appointmentId;
        this.consultationTime = consultationTime;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.consultationFee = consultationFee;
        this.admissionNeeded = admissionNeeded;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public LocalDate getConsultationTime() {
        return consultationTime;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public boolean isAdmissionNeeded() {
        return admissionNeeded;
    }

    public void setAdmissionNeeded(boolean admissionNeeded) {
        this.admissionNeeded = admissionNeeded;
    }

    @Override
    public String toString() {
        return "Consultation{" +
                "appointmentId='" + appointmentId + '\'' +
                ", consultationTime=" + consultationTime +
                ", diagnosis='" + diagnosis + '\'' +
                ", prescription='" + prescription + '\'' +
                ", consultationFee=" + consultationFee + '\'' +
                ", admissionNeeded=" + admissionNeeded +
                '}';
    }

    public double getConsultationFee() {
        return consultationFee;
    }
}
