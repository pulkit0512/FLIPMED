package DataStore.Impl;

import DataObjects.Patient;
import DataStore.PatientDataStore;

import java.util.HashMap;
import java.util.Map;

public class PatientDataStoreImpl implements PatientDataStore {
    private static final Map<Integer, Patient> patientDB = new HashMap<>();
    @Override
    public void addPatient(Patient patient) {
        patientDB.put(patient.getPatientId(), patient);
    }

    @Override
    public Patient getPatient(int patientId) {
        return patientDB.get(patientId);
    }

    @Override
    public void updatePatient(Patient patient) {
        patientDB.put(patient.getPatientId(), patient);
    }
}
