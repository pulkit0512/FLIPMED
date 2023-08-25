package Service.Impl;

import DataObjects.Booking;
import DataStore.BookingDataStore;
import DataStore.Impl.BookingDataStoreImpl;
import Service.ShowBookedAppointmentService;

import java.util.List;

public class ShowBookedAppointmentServiceForDoctor implements ShowBookedAppointmentService {
    private static final BookingDataStore bookingDataStore = new BookingDataStoreImpl();

    @Override
    public void showBookedAppointments(String docName) {
        List<Booking> bookings = bookingDataStore.getAllBookings();

        bookings.stream()
                .filter(booking -> booking.getDoctor().getName().equalsIgnoreCase(docName))
                .forEach(booking -> System.out.println(booking.getBookingId() + " "
                        + booking.getPatient().getName() + " " + booking.getTimeSlot()));
    }
}
