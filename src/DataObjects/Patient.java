package DataObjects;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Patient {
    private static int uniqueId = 0;
    private final int patientId;
    private String name;
    Map<Doctor, Set<String>> bookedSlots;

    public Patient() {
        uniqueId++;
        this.patientId = uniqueId;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Doctor, Set<String>> getBookedSlots() {
        if (bookedSlots == null) {
            bookedSlots = new HashMap<>();
        }
        return bookedSlots;
    }
}
