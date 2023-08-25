import DataObjects.Specialization;
import Service.*;
import Service.Impl.*;
import Utils.TimeConversionUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    private static final File FILE = new File("/Users/pulkitarora/learning/FLIPMED/input.txt");
    private static final DoctorService doctorService = new DoctorServiceImpl();
    private static final PatientService patientService = new PatientServiceImpl();
    private static final BookingService bookingService = new BookingServiceImpl();
    private static final ShowAvailabilityService showAvailabilityByTime = new ShowAvailabilityByTime();
    private static final ShowAvailabilityService showAvailabilityByRating = new ShowAvailabilityByRating();
    private static final ShowBookedAppointmentService showBookedAppointmentForPatient = new ShowBookedAppointmentForPatient();
    private static final ShowBookedAppointmentService showBookedAppointmentForDoctor = new ShowBookedAppointmentServiceForDoctor();
    private static final TimeConversionUtil timeUtil = new TimeConversionUtil();

    private static final Scanner sc;

    static {
        try {
            sc = new Scanner(FILE);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        System.out.println("Welcome to Hospital Management System.");

        while(sc.hasNextLine()) {
            String input = sc.nextLine();
            System.out.println("===" + input + "===");
            String[] params = input.split(" ");

            if (params[0].equalsIgnoreCase("registerDoc")) {
                String docName = params[1].toUpperCase();
                String specialization = params[2].toUpperCase();
                Specialization specializationType = getValidSpecialization(specialization);
                if (specializationType == null) {
                    continue;
                }
                doctorService.registerDoctor(docName, specializationType);
            } else if (params[0].equalsIgnoreCase("markDocAvail")) {
                int docId = Integer.parseInt(params[1]);
                String[] slots = new String[params.length-2];
                int idx = 0;
                for(int i=2;i<params.length;i++) {
                    String slot = params[i];
                    String[] duration = slot.split("-");
                    LocalTime startTime = timeUtil.convertToLocalTime(duration[0]);
                    LocalTime difference = timeUtil.convertToLocalTime(duration[1]).minusMinutes(30);
                    if (difference.isBefore(startTime) || difference.isAfter(startTime)) {
                        System.out.println("Not a valid slot, only 30minutes slot available");
                        break;
                    } else {
                        slots[idx] = duration[0];
                        idx++;
                    }
                }

                if (idx == slots.length) {
                    doctorService.updateDoctorAvailability(docId, slots);
                }
            } else if (params[0].equalsIgnoreCase("showAvailByspeciality")) {
                String specialization = params[1].toUpperCase();
                String strategy = params[2].toUpperCase();
                Specialization specializationType = getValidSpecialization(specialization);
                if (specializationType == null) {
                    continue;
                }
                if (strategy.equalsIgnoreCase("Time")) {
                    showAvailabilityByTime.showAvailability(specialization);
                } else {
                    showAvailabilityByRating.showAvailability(specialization);
                }
            } else if (params[0].equalsIgnoreCase("registerPatient")) {
                String patientName = params[1].toUpperCase();
                patientService.registerPatient(patientName);
            } else if (params[0].equalsIgnoreCase("bookAppointment")) {
                int patientId = Integer.parseInt(params[1]);
                int docId = Integer.parseInt(params[2]);
                String slot = params[3];
                boolean waitList = false;
                if (params.length == 5) {
                    waitList = Boolean.parseBoolean(params[4]);
                }
                bookingService.bookAppointment(patientId, docId, slot, waitList);
            } else if (params[0].equalsIgnoreCase("cancelBookingId")) {
                int bookingId = Integer.parseInt(params[1]);
                bookingService.cancelBooking(bookingId);
            } else if (params[0].equalsIgnoreCase("showAppointmentsBooked")) {
                String name = params[1].toUpperCase();
                String type = params[2].toUpperCase();
                if (type.equalsIgnoreCase("Doctor")) {
                    showBookedAppointmentForDoctor.showBookedAppointments(name);
                } else if (type.equalsIgnoreCase("Patient")) {
                    showBookedAppointmentForPatient.showBookedAppointments(name);
                } else {
                    System.out.println("Not a valid command.");
                }
            }
        }
    }

    private static Specialization getValidSpecialization(String specialization) {
        Specialization specializationType = null;
        if (specialization.equalsIgnoreCase(Specialization.Cardiologist.toString())) {
            specializationType = Specialization.Cardiologist;
        } else if (specialization.equalsIgnoreCase(Specialization.Dermatologist.toString())) {
            specializationType = Specialization.Dermatologist;
        } else if (specialization.equalsIgnoreCase(Specialization.Orthopedic.toString())) {
            specializationType = Specialization.Orthopedic;
        } else if (specialization.equalsIgnoreCase(Specialization.GeneralPhysician.toString())) {
            specializationType = Specialization.GeneralPhysician;
        } else {
            System.out.println("Not a valid specialization.");
        }
        return specializationType;
    }
}