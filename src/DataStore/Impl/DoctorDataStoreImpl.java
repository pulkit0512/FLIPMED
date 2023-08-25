package DataStore.Impl;

import DataObjects.Doctor;
import DataStore.DoctorDataStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorDataStoreImpl implements DoctorDataStore {
    private static final Map<Integer, Doctor> doctorDB = new HashMap<>();
    private static final Map<String, List<Doctor>> specializationDB = new HashMap<>();

    @Override
    public void addDoctor(Doctor doctor) {
        doctorDB.put(doctor.getDocId(), doctor);
        List<Doctor> doctors = specializationDB.getOrDefault(doctor.getSpecialization().toString(), new ArrayList<>());
        doctors.add(doctor);
        specializationDB.put(doctor.getSpecialization().toString().toUpperCase(), doctors);
    }

    @Override
    public Doctor getDoctor(int docId) {
        return doctorDB.get(docId);
    }

    @Override
    public void updateDoctorDetails(Doctor doctor) {
        doctorDB.put(doctor.getDocId(), doctor);
    }

    @Override
    public List<Doctor> getDoctorBySpecialization(String specialization) {
        return specializationDB.getOrDefault(specialization, new ArrayList<>());
    }
}
