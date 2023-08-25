package Service.Impl;

import DataObjects.Doctor;
import DataObjects.Specialization;
import DataStore.DoctorDataStore;
import DataStore.Impl.DoctorDataStoreImpl;
import Service.DoctorService;

import java.util.Map;
import java.util.Random;

public class DoctorServiceImpl implements DoctorService {
    private static final DoctorDataStore doctorDataStore = new DoctorDataStoreImpl();
    private static final Random random = new Random();

    @Override
    public void registerDoctor(String doctorName, Specialization specialization) {
        Doctor doctor = new Doctor();
        doctor.setName(doctorName);
        doctor.setRating(random.nextDouble(0.0, 5.0));
        doctor.setSpecialization(specialization);
        doctorDataStore.addDoctor(doctor);
        System.out.println("Welcome Dr." + doctorName);
    }

    @Override
    public void updateDoctorAvailability(int docId, String[] availableSlots) {
        Doctor doctor = doctorDataStore.getDoctor(docId);
        Map<String, Boolean> slots = doctor.getSlots();
        for(String slot : availableSlots) {
            if(slots.containsKey(slot)) {
                System.out.println("Slot Already present for the doctor.");
                continue;
            }
            slots.put(slot, true);
        }

        doctorDataStore.updateDoctorDetails(doctor);
        System.out.println("Done Doctor.");
    }
}
