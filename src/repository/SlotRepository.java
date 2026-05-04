package repository;

import model.Slot;


import java.util.*;

public class SlotRepository {

    private static Map<String, List<Slot>> slotDoctorMap = new HashMap<>();

    // slotId -> current booking count
    private static Map<String, Integer> slotBookingCount = new HashMap<>();

    private static final int MAX_CAPACITY = 6;

    public static int getMaxCapacity() {
        return MAX_CAPACITY;
    }

    public static Map<String, List<Slot>> getSlotDoctorMap() {
        return new HashMap<>(slotDoctorMap);
    }

    public static Map<String, Integer> getSlotBookingCount() {
        return new HashMap<>(slotBookingCount);
    }

    // Get slots by doctor
    public static List<Slot> getSlotsByDoctorId(String doctorId) {
        return slotDoctorMap.getOrDefault(doctorId, new ArrayList<>());
    }

    // Get all slots
    public static List<Slot> getAllSlots() {
        List<Slot> all = new ArrayList<>();
        for (List<Slot> list : slotDoctorMap.values()) {
            all.addAll(list);
        }
        return all;
    }

    // Check availability
    public static boolean isSlotAvailable(String slotId) {
        return slotBookingCount.getOrDefault(slotId, 0) < MAX_CAPACITY;
    }

    // Increment booking
    public static boolean bookSlot(String slotId) {
        int current = slotBookingCount.getOrDefault(slotId, 0);

        if (current >= MAX_CAPACITY) {
            return false;
        }


        slotBookingCount.put(slotId, current + 1);
        return true;
    }

    // Decrement booking (for cancel)
    public static void releaseSlot(String slotId) {
        int current = slotBookingCount.getOrDefault(slotId, 0);

        if (current > 0) {
            slotBookingCount.put(slotId, current - 1);
        }
    }

    // Get available slots only
    public static List<Slot> getAvailableSlotsByDoctor(String doctorId) {

        List<Slot> slots = getSlotsByDoctorId(doctorId);
        List<Slot> available = new ArrayList<>();

        for (Slot s : slots) {
            if (isSlotAvailable(s.getSlotId())) {
                available.add(s);
            }
        }

        return available;
    }

    // Find slot by ID
    public static Slot findBySlotId(String slotId) {

        for (List<Slot> list : slotDoctorMap.values()) {
            for (Slot s : list) {
                if (s.getSlotId().equals(slotId)) {
                    return s;
                }
            }
        }

        return null;
    }
}