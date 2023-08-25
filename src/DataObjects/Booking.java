package DataObjects;

public class Booking {
    private static int uniqueId = 0;
    private final int bookingId;
    private Patient patient;
    private Doctor doctor;
    private String timeSlot;
    private boolean waitList;

    public Booking() {
        uniqueId++;
        this.bookingId = uniqueId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public boolean isWaitList() {
        return waitList;
    }

    public void setWaitList(boolean waitList) {
        this.waitList = waitList;
    }
}
