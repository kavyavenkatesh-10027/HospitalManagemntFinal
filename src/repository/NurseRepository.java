package repository;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class NurseRepository {

    private static final List<Nurse> allNurses = new ArrayList<>();

    public static List<Nurse> getAllNurses() {
        return new ArrayList<>(allNurses);
    }

    public static Nurse findById(String nurseId) {
        for (Nurse n : allNurses) {
            if (n.getId().equals(nurseId)) {
                return n;
            }
        }
        return null;
    }

    public static Nurse findByEmail(String email) {
        for (Nurse n : allNurses) {
            if (n.getEmailId().equals(email)) {
                return n;
            }
        }
        return null;
    }

    public static List<Ward> wardsAssignedToNurse(String nurseId){
        Nurse nurse = findById(nurseId);
        return nurse != null ? nurse.getAssignedWards() : new ArrayList<>();
    }

    public static void addNurse(Nurse nurse){
        allNurses.add(nurse);
    }

    public static void deleteNurse(String nurseId){
        allNurses.removeIf(n -> n.getId().equals(nurseId));
    }

    public static void editNurse(String nurseId, Nurse updatedNurse){
        for (int i = 0; i < allNurses.size(); i++) {
            if (allNurses.get(i).getId().equals(nurseId)) {
                allNurses.set(i, updatedNurse);
                return;
            }
        }
    }
}