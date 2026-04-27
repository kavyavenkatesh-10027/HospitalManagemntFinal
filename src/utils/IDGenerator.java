package utils;

public class IDGenerator {
    private static long adminId=1;
    private static long receptionistId=1;
    private static long patientId=1;
    private static long departmentId=1;
    private static long doctorId=1;
    private static long nurseId=1;
    private static long appointmentId=1;
    private static long consultationId=1;
    private static long slotId=1;
    private static long tokenId=1;
    private static long billId=1;
    public static String generateAdminId() {
        return "admin-"+adminId++;
    }

    public static String generateReceptionistId() {
        return "recept-"+receptionistId++;
    }

    public static String generatePatientId() {
        return "patient-"+patientId++;
    }

    public static String generateDepartmentId() {
        return "dept-"+departmentId++;
    }

    public static String generateDoctorId() {
        return "doctor-"+doctorId++;
    }

    public static String generateNurseId() {
        return "nurse-"+nurseId++;
    }

    public static String generateAppointmentId() {
        return "appoint-"+appointmentId++;
    }

    public static String generateConsultationId() {
        return "consult-"+consultationId++;
    }

    public static String generateSlotId() {
        return "slot"+ slotId++;
    }

    public static long generateToken() {
        return tokenId++;
    }

    public static String generateBillId() {
        return "bill-"+billId++;
    }
}
