package model;

import utils.IDGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Patient extends User{
    private final String patientID;
    private final BloodGroup bloodGroup;
    private final String fatherName;
    private final String motherName;
    private String guardianName;
    private String guardianNum;
    private final ArrayList<Appointment> patientAppointments ;
    private final List<Consultation> patientRecord = new ArrayList<>();

    public Patient(String name, String gender, LocalDate dob, String phnNo, String emailId, BloodGroup bloodGroup, String fatherName, String motherName, String guardianName, String guardianNum, Role role) {
        super(name, gender, dob, phnNo, emailId, role);
        this.patientID = IDGenerator.generatePatientId();
        this.bloodGroup = bloodGroup;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.guardianName = guardianName;
        this.guardianNum = guardianNum;
        this.patientAppointments=new ArrayList<>();
        role = Role.PATIENT;
    }

    @Override
    public String getId() {
        return patientID;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public String getGuardianNum() {
        return guardianNum;
    }

    public void setGuardianNum(String guardianNum) {
        this.guardianNum = guardianNum;
    }

    public List<Appointment> getPatientAppointments() {
        return patientAppointments;
    }

//    public void addNewAppointment(Appointment newlyBookedAppointment) {
//        patientAppointments.add(newlyBookedAppointment);
//    }
//
//    public void cancelAppointment(Appointment toDeleteAppointment){
//        patientAppointments.remove(toDeleteAppointment);
//    }
    public List<Consultation> getPatientRecord() {
        return patientRecord;
    }


    public enum BloodGroup{
        A_positive,
        A_negative,
        B_positive,
        B_negative,
        O_positive,
        O_negative,
        AB_positive,
        AB_negative
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientID='" + patientID + '\'' +
                "name= '" + getName() + '\'' +
                "phone Number '" +getPhnNo() + '\'' +
                ", bloodGroup=" + bloodGroup +
                ", fatherName='" + fatherName + '\'' +
                ", motherName='" + motherName + '\'' +
                ", guardianName='" + guardianName + '\'' +
                ", guardianNum='" + guardianNum + '\'' +
                ", patientAppointments=" + patientAppointments +
                ", patientRecord=" + patientRecord +
                ", joinedAt=" + getJoinedAt() +
                '}';
    }
}
