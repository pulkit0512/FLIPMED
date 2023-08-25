package Service.Impl;

import DataObjects.Patient;
import DataStore.Impl.PatientDataStoreImpl;
import DataStore.PatientDataStore;
import Service.PatientService;

public class PatientServiceImpl implements PatientService {
    private static final PatientDataStore patientDataStore = new PatientDataStoreImpl();

    @Override
    public void registerPatient(String patientName) {
        Patient patient = new Patient();
        patient.setName(patientName);
        patientDataStore.addPatient(patient);
        System.out.println(patientName + " registered.");
    }
}
