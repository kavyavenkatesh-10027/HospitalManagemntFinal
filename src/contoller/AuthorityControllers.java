package contoller;

import model.User;

public interface AuthorityControllers<T extends User> extends Controller<T> {
    public abstract void admitPatient(T currentUser);

    public abstract void dischargePatient(T currentUser);

    public abstract void viewDiagnosisAndPrescription(T currentUser);

    public abstract void viewAdmissions(T currentUser);

    public default void triggerBilling(T currentUser){

    }
}
