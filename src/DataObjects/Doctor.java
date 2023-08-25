package DataObjects;

import java.util.HashMap;
import java.util.Map;

public class Doctor {
    private static int uniqueId = 0;
    private final int docId;
    private String name;
    private Specialization specialization;
    Map<String, Boolean> slots;
    private double rating;

    public Doctor() {
        uniqueId++;
        this.docId = uniqueId;
    }

    public int getDocId() {
        return docId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public Map<String, Boolean> getSlots() {
        if (slots == null) {
            slots = new HashMap<>();
        }
        return slots;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
