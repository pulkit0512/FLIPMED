package Service;

import DataObjects.Specialization;

public interface DoctorService {
    void registerDoctor(String doctorName, Specialization specialization);
    void updateDoctorAvailability(int docId, String[] availableSlots);
}
