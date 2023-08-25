package DataStore;

import DataObjects.Booking;

import java.util.List;

public interface BookingDataStore {
    void addBookingToDB(Booking booking);
    void addBookingToWaitList(Booking booking);
    void cancelBooking(int bookingId);
    Booking getBooking(int bookingId);
    Booking getBookingFromWaitList();

    List<Booking> getAllBookings();
}
