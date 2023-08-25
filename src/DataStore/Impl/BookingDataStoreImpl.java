package DataStore.Impl;

import DataObjects.Booking;
import DataStore.BookingDataStore;

import java.util.*;

public class BookingDataStoreImpl implements BookingDataStore {
    private static final Queue<Booking> waitListQueue = new LinkedList<>();
    private static final Map<Integer, Booking> bookingDB = new HashMap<>();

    @Override
    public void addBookingToDB(Booking booking) {
        bookingDB.put(booking.getBookingId(), booking);
    }

    @Override
    public void addBookingToWaitList(Booking booking) {
        waitListQueue.add(booking);
    }

    @Override
    public void cancelBooking(int bookingId) {
        bookingDB.remove(bookingId);
    }

    @Override
    public Booking getBooking(int bookingId) {
        return bookingDB.get(bookingId);
    }

    @Override
    public Booking getBookingFromWaitList() {
        return waitListQueue.isEmpty() ? null : waitListQueue.poll();
    }

    @Override
    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookingDB.values());
    }
}
