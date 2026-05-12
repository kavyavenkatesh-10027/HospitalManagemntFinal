package service;

import model.Appointment;
import model.Doctor;
import model.Patient;
import model.Slot;
import repository.AppointmentRepository;
import repository.DoctorRepository;
import repository.PatientRepository;

import java.time.DayOfWeek;
import java.util.*;
import java.util.stream.Collectors;

public class AppointmentService {

    public void bookAppointment(String patientId, String departmentId, String doctorId, Slot appointmentTiming, Appointment.STATUS status){
        Appointment newAppointment = new Appointment(patientId, departmentId, doctorId, appointmentTiming, Appointment.STATUS.CONFIRMED);
        AppointmentRepository.addAppointment(newAppointment);
        for(Appointment ap: AppointmentRepository.getAllAppointments()){
            System.out.println(ap);
        }
        Patient p=PatientRepository.findById(patientId);
        if(p!=null){
            p.getPatientAppointments().add(newAppointment);
        }
        Doctor d=DoctorRepository.findById(doctorId);
        if(d!=null){
            d.addAppointment(newAppointment);
        }


    }



    public List<Appointment> viewAllAppointments(){
        return AppointmentRepository.getAllAppointments();

    }

    public List<Appointment> viewAppointmentsByPatientId(String patientId){
        return PatientRepository.viewPatientAppointments(patientId);
    }

    public List<Appointment> viewAppointmentsByDoctorId(String doctorId){
        return AppointmentRepository.appointmentsByDoctorId(doctorId);

    }

    public List<Appointment> viewAppointmentByDepartmentId(String deptId){

        return AppointmentRepository.appointmentByDepartmentId(deptId);

    }

    public List<Appointment> viewAppointmentByDay(DayOfWeek onDay){

    return AppointmentRepository.appointmentByDay(onDay);
    }

    public List<Appointment> viewAppointmentBySlotId(String slotId){

        return AppointmentRepository.appointmentBySlotId(slotId);
    }

    public void updateAppointment(String patientId, String appointmentId, Slot appointmentTiming){
        Appointment concernedAppointment = AppointmentRepository.findAppointmentsByAppointmentId(appointmentId);
        concernedAppointment.setAppointmentTiming(appointmentTiming);
    }

    public void deleteAppointment(String patientId, String appointmentId){
        Appointment appointmentsToDelete = AppointmentRepository.findAppointmentsByAppointmentId(appointmentId);
        if(appointmentsToDelete!=null){
            appointmentsToDelete.setStatus(Appointment.STATUS.CANCELLED);

            Patient p=PatientRepository.findById(appointmentsToDelete.getPatientId());
            if(p!=null) {
                p.getPatientAppointments().remove(appointmentsToDelete);
            }

            Doctor d=DoctorRepository.findById(appointmentsToDelete.getDoctorId());
            if(d!=null) {
                d.cancelAppointment(appointmentsToDelete);
            }
        }
    }
}
