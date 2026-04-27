package model;

import utils.DateUtils;
import utils.IDGenerator;

import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class Slot {
    private final String slotId;
    private String doctorId;
    private Time startTime;
    private Time endTime;
    private DayOfWeek day;
    private LocalDate date = DateUtils.getNextSlotDate(day);

    public Slot(String slotId, String doctorId, Time startTime, Time endTime, DayOfWeek day) {
        this.slotId = IDGenerator.generateSlotId();
        this.doctorId = doctorId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.day = day;
    }

    public String getSlotId() {
        return slotId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Slot{" +
                "slotId='" + slotId + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", day=" + day +
                ", date=" + date +
                '}';
    }
}
