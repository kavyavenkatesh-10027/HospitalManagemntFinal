package contoller;

import model.*;
import repository.AppointmentRepository;

import java.util.List;

public class PatientController implements Controller{

    private User currentUser;

    public PatientController(User user) {
        this.currentUser = user;
    }

    @Override
    public void viewProfile() {

    }

    @Override
    public void updateProfile() {

    }

    @Override
    public void start() {
        System.out.println("Welcome to Sugah Hospital\n\nWhere our first priority is your health,\n     and we spend our blood, sweat and tears achieving it\n\n");
        System.out.println("How shall we help you?\n\n1. Book Appointment\n2.View Appointment\n 3. Reschedule Appointment\n 4. Delete Appointment\n 5. View Profile\n 6. Update Profile\n 7. View Consultation\n 8. View All Visits");
    }

    @Override
    public void viewAppointments(String patId) {
        List<Appointment> appointmentList = AppointmentRepository.appointmentsByPatientId(patId);
        for (int appointment = 0; appointment < appointmentList.size(); appointment++) {
            System.out.println(appointmentList.get(appointment));
        }
    }

    @Override
    public void bookAppointments() {

    }

    @Override
    public void rescheduleAppointments() {

    }

    public Consultation viewConsultation(){
      return null;
    }

    public List<Consultation> viewAllConsultation(){
      return null;
    }
}
