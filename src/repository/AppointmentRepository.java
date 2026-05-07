package repository;

import model.Appointment;
import model.Slot;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AppointmentRepository {
    private static final List<Appointment> allAppointments = new ArrayList<>();



    public static List<Appointment> getAllAppointments(){
        return new ArrayList<>(allAppointments);
    }

    public static Appointment findAppointmentsByAppointmentId(String appointmentId){
        for (Appointment appoint : allAppointments){
            if(appoint.getAppointmentId().equals(appointmentId)) {
                return appoint;
            }
        }
        return null;
    }

    public static List<Appointment> findAppointmentsByAppointmentStatus(Appointment.STATUS status){
        return allAppointments.stream()
                .filter(a -> a.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    public static List<Appointment> findAppointmentsByPatientId(String patientId){
        return allAppointments.stream()
                .filter(a -> a.getPatientId().equals(patientId))
                .collect(Collectors.toList());

    }

    public static List<Appointment> appointmentsByDoctorId(String doctorId){
        return allAppointments.stream()
                .filter(a -> a.getDoctorId().equals(doctorId))
                .collect(Collectors.toList());

    }

    public static List<Appointment> appointmentByDepartmentId(String dept){
        return allAppointments.stream()
                .filter(a -> a.getDepartmentId().equals(a.getDepartmentId()))
                .collect(Collectors.toList());

    }

    public static List<Appointment> appointmentByDay(DayOfWeek day){
        return allAppointments.stream()
                .filter(a -> a.getAppointmentTiming().getDay().equals(day))
                .collect(Collectors.toList());

    }

    public static List<Appointment> appointmentBySlotId(String slotId){
        return allAppointments.stream()
                .filter(a -> a.getAppointmentTiming().getSlotId().equals(slotId))
                .collect(Collectors.toList());

    }

    public static void addAppointment(Appointment bookedAppointment){
        allAppointments.add(bookedAppointment);

    }

    public static boolean updateAppointment(String appointId,Slot newSlot, String newDocId) {
        Appointment concernedAppointment = findAppointmentsByAppointmentId(appointId);
        if (concernedAppointment != null) {
            concernedAppointment.setAppointmentTiming(newSlot);
            concernedAppointment.setDoctorId(newDocId);
            return true;
        }
        return false;
    }

    public static void deleteAppointment(String appointmentId){
        Appointment toDeleteAppoint = findAppointmentsByAppointmentId(appointmentId);
        for (int i = 0; i < allAppointments.size(); i++) {
            if(allAppointments.get(i) == toDeleteAppoint){
                allAppointments.remove(i);
                return;
            }
        }
    }
}
