package Service.Impl;

import DataObjects.Doctor;
import DataStore.DoctorDataStore;
import DataStore.Impl.DoctorDataStoreImpl;
import Service.ShowAvailabilityService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ShowAvailabilityByTime implements ShowAvailabilityService {
    private static final DoctorDataStore doctorDataStore = new DoctorDataStoreImpl();

    @Override
    public void showAvailability(String specialization) {
        List<Doctor> doctorList = doctorDataStore.getDoctorBySpecialization(specialization);

        TreeMap<String, List<String>> availableDoctors = new TreeMap<>();
        for(Doctor doctor : doctorList) {
            Map<String, Boolean> slots = doctor.getSlots();
            for (Map.Entry<String, Boolean> slot : slots.entrySet()) {
                if (slot.getValue()) {
                    availableDoctors.putIfAbsent(slot.getKey(), new ArrayList<>());
                    availableDoctors.get(slot.getKey()).add(doctor.getName());
                }
            }
        }

        for(Map.Entry<String, List<String>> entry : availableDoctors.entrySet()) {
            for(String docName : entry.getValue()) {
                System.out.println(docName + ": " + entry.getKey());
            }
        }
    }
}
