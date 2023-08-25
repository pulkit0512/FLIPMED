package Service.Impl;

import DataObjects.Booking;
import DataStore.BookingDataStore;
import DataStore.Impl.BookingDataStoreImpl;
import Service.ShowBookedAppointmentService;

import java.util.List;

public class ShowBookedAppointmentForPatient implements ShowBookedAppointmentService {

    private static final BookingDataStore bookingDataStore = new BookingDataStoreImpl();

    @Override
    public void showBookedAppointments(String patientName) {
        List<Booking> bookings = bookingDataStore.getAllBookings();

        bookings.stream()
                .filter(booking -> booking.getPatient().getName().equalsIgnoreCase(patientName))
                .forEach(booking -> System.out.println(booking.getBookingId() + " "
                        + booking.getDoctor().getName() + " " + booking.getTimeSlot()));
    }
}
