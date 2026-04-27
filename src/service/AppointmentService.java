package service;

import model.Appointment;
import model.Patient;
import model.Slot;
import repository.AppointmentRepository;
import repository.DoctorRepository;
import repository.PatientRepository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AppointmentService {

    public void bookAppointment(String patientId, String departmentId, String doctorId, Slot appointmentTiming, Appointment.STATUS status){
        Appointment newAppointment = new Appointment(patientId, departmentId, doctorId, appointmentTiming, Appointment.STATUS.CONFIRMED);
        AppointmentRepository.addAppointment(newAppointment);

        Objects.requireNonNull(PatientRepository.findById(patientId)).addNewAppointment(newAppointment);

        Objects.requireNonNull(DoctorRepository.findById(doctorId)).addAppointment(newAppointment);
    }

    public List<Appointment> viewAllAppointments(){
        return AppointmentRepository.getAllAppointments();

    }

    public List<Appointment> viewAppointmentsByPatientId(String patientId){
        return PatientRepository.viewPatientAppointments(patientId);

    }

    public List<Appointment> viewAppointmentsByDoctorId(String doctorId){
        List<Appointment> allAppointments = AppointmentRepository.getAllAppointments();

        return allAppointments.stream()
                .filter(a -> a.getDoctorId().equals(doctorId))
                .collect(Collectors.toList());

    }

    public List<Appointment> viewAppointmentByDepartmentId(String deptId){
        List<Appointment> allAppointments = AppointmentRepository.getAllAppointments();

        return allAppointments.stream()
                .filter(a -> a.getDepartmentId().equals(deptId))
                .collect(Collectors.toList());

    }

    public List<Appointment> viewAppointmentByDay(DayOfWeek onDay){
        List<Appointment> allAppointments = AppointmentRepository.getAllAppointments();

        return allAppointments.stream()
                .filter(a -> a.getAppointmentTiming().getDay().equals(onDay))
                .collect(Collectors.toList());

    }

    public List<Appointment> viewAppointmentBySlotId(String slotId){
        List<Appointment> allAppointments = AppointmentRepository.getAllAppointments();

        return allAppointments.stream()
                .filter(a -> a.getAppointmentTiming().getSlotId().equals(slotId))
                .collect(Collectors.toList());
    }

    public void updateAppointment(String patientId, String appointmentId, Slot appointmentTiming){
        Appointment concernedAppointment = AppointmentRepository.findAppointmentsByAppointmentId(appointmentId);
        concernedAppointment.setAppointmentTiming(appointmentTiming);
    }

    public void deleteAppointment(String patientId, String appointmentId){
        List<Appointment> allAppointments = AppointmentRepository.getAllAppointments();
        List<Appointment> patientSpecific = PatientRepository.findById(patientId).getPatientAppointments();
        for (Appointment appoint: allAppointments) {
            if(appoint.getAppointmentId().equals(appointmentId)){
                allAppointments.remove(appoint);
                break;
            }
        }
        for (Appointment appoint : patientSpecific) {
            if(appoint.getAppointmentId().equals(appointmentId)){
                allAppointments.remove(appoint);
                break;
            }
        }
    }
}
