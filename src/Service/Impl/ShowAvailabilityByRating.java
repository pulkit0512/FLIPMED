package Service.Impl;

import DataObjects.Doctor;
import DataStore.DoctorDataStore;
import DataStore.Impl.DoctorDataStoreImpl;
import Service.ShowAvailabilityService;

import java.util.Comparator;
import java.util.List;

public class ShowAvailabilityByRating implements ShowAvailabilityService {
    private static final DoctorDataStore doctorDataStore = new DoctorDataStoreImpl();

    @Override
    public void showAvailability(String specialization) {
        List<Doctor> doctorList = doctorDataStore.getDoctorBySpecialization(specialization);

        doctorList.sort(Comparator.comparingDouble(Doctor::getRating));

        doctorList.forEach(doctor -> System.out.println(doctor.getName() + " " + doctor.getRating() + " " + doctor.getSlots().keySet()));
    }
}
