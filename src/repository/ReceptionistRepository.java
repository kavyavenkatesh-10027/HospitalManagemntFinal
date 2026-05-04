package repository;

import model.Patient;
import model.Receptionist;

import java.util.ArrayList;
import java.util.List;

public class ReceptionistRepository {

    private static final List<Receptionist> allReceptionists = new ArrayList<>();

    public static List<Receptionist> getAllReceptionists() {
        return new ArrayList<>(allReceptionists);
    }

    public static Receptionist findById(String receptionistId) {
        for (Receptionist r : allReceptionists) {
            if (r.getId().equals(receptionistId)) {
                return r;
            }
        }
        return null;
    }

    public static Receptionist findByEmail(String email) {
        for (Receptionist r : allReceptionists) {
            if (r.getEmailId().equals(email)) {
                return r;
            }
        }
        return null;
    }


    public static void addReceptionist(Receptionist receptionist){
        allReceptionists.add(receptionist);
    }

    public static void deleteReceptionist(String receptionistId){
        allReceptionists.removeIf(r -> r.getId().equals(receptionistId));
    }

    public static void editReceptionist(String receptionistId, Receptionist updatedReceptionist){
        for (int i = 0; i < allReceptionists.size(); i++) {
            if (allReceptionists.get(i).getId().equals(receptionistId)) {
                allReceptionists.set(i, updatedReceptionist);
                return;
            }
        }
    }
}