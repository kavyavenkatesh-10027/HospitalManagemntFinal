package model;

import utils.IDGenerator;
import utils.Shifts;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Nurse extends User{
    private final String nurseId;
    private final List<Ward> assignedWards = new ArrayList<>();
    private Shifts.Shift shift;

    public Nurse(String name, String gender, LocalDate dob, String phnNo, String emailId, Shifts.Shift shift) {
        super(name, gender, dob, phnNo, emailId);
        this.nurseId = IDGenerator.generateNurseId();
        this.shift = shift;
    }

    public String getId() {
        return nurseId;
    }

    public Shifts.Shift getShift() {
        return shift;
    }

    public void setShift(Shifts.Shift shift) {
        this.shift = shift;
    }

    public List<Ward> getAssignedWards() {
        return assignedWards;
    }

    public void addAssignedWards(Ward newWard) {
        assignedWards.add(newWard);
    }

    @Override
    public String toString() {
        return "Nurse{" +
                "nurseId='" + nurseId + '\'' +
                ", assignedWards=" + assignedWards +
                ", shift=" + shift +
                ", joinedAt=" + joinedAt +
                '}';
    }
}
