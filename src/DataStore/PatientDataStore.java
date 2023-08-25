package DataStore;

import DataObjects.Patient;

public interface PatientDataStore {
    void addPatient(Patient patient);
    Patient getPatient(int patientId);
    void updatePatient(Patient patient);
}
