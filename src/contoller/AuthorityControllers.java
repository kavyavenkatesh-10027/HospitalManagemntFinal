package contoller;

public interface AuthorityControllers extends Controller {
    public abstract void admitPatient();

    public abstract void dischargePatient();

    public abstract void viewDiagnosis();

    public abstract void viewAdmissions();

    public default void triggerBilling(){

    }
}
