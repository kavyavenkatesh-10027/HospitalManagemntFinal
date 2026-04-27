package contoller;

import model.User;


public interface Controller {
    User currentUser = null;

    public abstract void viewProfile();

    public abstract void updateProfile();

    public abstract void start();

    public abstract void viewAppointments(String condition);

    public abstract void bookAppointments();

    public abstract void rescheduleAppointments();

    public default void logout(){

    }
}
