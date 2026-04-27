package model;

import utils.IDGenerator;

import java.time.LocalDate;

public class Receptionist extends User{
    private final String receptionistId;
    private Shift shift;

    public Receptionist(String name, String gender, LocalDate dob, String phnNo, String emailId, Shift shift) {
        super(name, gender, dob, phnNo, emailId);
        this.receptionistId = IDGenerator.generateReceptionistId();
        this.shift = shift;
    }

    public String getId() {
        return receptionistId;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    private enum Shift{
        GENERAL,
        MORNING,
        AFTERNOON,
        NIGHT
    }

    @Override
    public String toString() {
        return "Receptionist{" +
                "receptionistId='" + receptionistId + '\'' +
                ", shift=" + shift +
                ", joinedAt=" + joinedAt +
                '}';
    }
}
