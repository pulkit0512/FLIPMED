package Service.Impl;

import DataObjects.Booking;
import DataObjects.Doctor;
import DataObjects.Patient;
import DataStore.BookingDataStore;
import DataStore.DoctorDataStore;
import DataStore.Impl.BookingDataStoreImpl;
import DataStore.Impl.DoctorDataStoreImpl;
import DataStore.Impl.PatientDataStoreImpl;
import DataStore.PatientDataStore;
import Service.BookingService;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BookingServiceImpl implements BookingService {
    private static final BookingDataStore bookingDataStore = new BookingDataStoreImpl();
    private static final DoctorDataStore doctorDataStore = new DoctorDataStoreImpl();
    private static final PatientDataStore patientDataStore = new PatientDataStoreImpl();

    @Override
    public void bookAppointment(int patientId, int docId, String slot, boolean waitList) {
        Booking booking = new Booking();
        Patient patient = patientDataStore.getPatient(patientId);
        boolean isPatientSlotAvailable = isPatientFree(patient, slot);
        if (!isPatientSlotAvailable) {
            System.out.println("Patient already a have booking for this slot.");
            return;
        }

        Doctor doctor = doctorDataStore.getDoctor(docId);
        boolean isDoctorSlotAvailable = doctor.getSlots().getOrDefault(slot, false);

        booking.setDoctor(doctor);
        booking.setPatient(patient);
        booking.setTimeSlot(slot);
        booking.setWaitList(waitList);

        if (isDoctorSlotAvailable) {
            updateConfirmationBookingDetails(doctor, slot, patient, booking);
        } else if (waitList) {
            bookingDataStore.addBookingToWaitList(booking);
        }
    }

    @Override
    public void cancelBooking(int bookingId) {
        Booking booking = bookingDataStore.getBooking(bookingId);
        Patient patient = booking.getPatient();
        Doctor doctor = booking.getDoctor();
        String slot = booking.getTimeSlot();

        bookingDataStore.cancelBooking(bookingId);

        patient.getBookedSlots().get(doctor).remove(slot);
        patientDataStore.updatePatient(patient);

        doctor.getSlots().put(slot, true);
        doctorDataStore.updateDoctorDetails(doctor);

        confirmWaitListBooking(doctor);
    }

    private boolean isPatientFree(Patient patient, String slot) {
        Map<Doctor, Set<String>> bookedSlots = patient.getBookedSlots();

        for(Map.Entry<Doctor, Set<String>> entry : bookedSlots.entrySet()) {
            if (entry.getValue().contains(slot)) {
                return false;
            }
        }
        return true;
    }

    private void updateConfirmationBookingDetails(Doctor doctor, String slot, Patient patient, Booking booking) {
        doctor.getSlots().put(slot, false);

        Set<String> bookedSlots = patient.getBookedSlots().getOrDefault(doctor, new HashSet<>());
        bookedSlots.add(slot);
        patient.getBookedSlots().put(doctor, bookedSlots);

        doctorDataStore.updateDoctorDetails(doctor);
        patientDataStore.updatePatient(patient);
        bookingDataStore.addBookingToDB(booking);
        System.out.println("Booking confirmed. " + booking.getBookingId());
    }

    private void confirmWaitListBooking(Doctor doctor) {
        Booking booking = bookingDataStore.getBookingFromWaitList();
        if (booking != null) {
            boolean isPatientFree = isPatientFree(booking.getPatient(), booking.getTimeSlot());
            if (!isPatientFree) {
                System.out.println("Patient already have a booking for this slot.");
                return;
            }
            updateConfirmationBookingDetails(doctor, booking.getTimeSlot(), booking.getPatient(), booking);
        }
    }
}
