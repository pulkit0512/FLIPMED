package Service;

public interface BookingService {
    void bookAppointment(int patientId, int docId, String slot, boolean waitList);
    void cancelBooking(int bookingId);
}
