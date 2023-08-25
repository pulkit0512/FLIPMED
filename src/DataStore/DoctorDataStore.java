package DataStore;

import DataObjects.Doctor;

import java.util.List;

public interface DoctorDataStore {
    void addDoctor(Doctor doctor);
    Doctor getDoctor(int docId);
    void updateDoctorDetails(Doctor doctor);
    List<Doctor> getDoctorBySpecialization(String specialization);
}
