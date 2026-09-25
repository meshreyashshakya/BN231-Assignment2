package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClinicTest {

    @TempDir
    Path tempDir;

    private Clinic clinic;

    @BeforeEach
    void setUp() {
        clinic = new Clinic(tempDir.toString());
    }

    @Test
    @DisplayName("A new patient can be added to the clinic")
    void addPatientStoresPatient() {
        boolean added = clinic.addPatient(new Patient("P001", "John Smith", "0412345678"));
        assertTrue(added);
        assertEquals(1, clinic.getPatients().size());
    }

    @Test
    @DisplayName("A duplicate patient ID is rejected")
    void addPatientRejectsDuplicateId() {
        clinic.addPatient(new Patient("P001", "John Smith", "0412345678"));
        boolean added = clinic.addPatient(new Patient("P001", "Another Person", "0400000000"));
        assertFalse(added);
        assertEquals(1, clinic.getPatients().size());
    }

    @Test
    @DisplayName("Linear search finds an existing patient by ID")
    void findPatientByIdReturnsMatch() {
        clinic.addPatient(new Patient("P001", "John Smith", "0412345678"));
        clinic.addPatient(new Patient("P002", "Sarah Lee", "0423456789"));

        Patient found = clinic.findPatientByID("P002");

        assertNotNull(found);
        assertEquals("Sarah Lee", found.getPatientName());
    }

    @Test
    @DisplayName("Linear search returns null when the ID does not exist")
    void findPatientByIdReturnsNullWhenMissing() {
        clinic.addPatient(new Patient("P001", "John Smith", "0412345678"));
        assertNull(clinic.findPatientByID("P999"));
    }

    @Test
    @DisplayName("An existing patient can be updated")
    void updatePatientChangesDetails() {
        clinic.addPatient(new Patient("P001", "John Smith", "0412345678"));

        boolean updated = clinic.updatePatient("P001", "John Smithson", "0499999999");

        assertTrue(updated);
        assertEquals("John Smithson", clinic.findPatientByID("P001").getPatientName());
        assertEquals("0499999999", clinic.findPatientByID("P001").getPhoneNumber());
    }

    @Test
    @DisplayName("An existing patient can be deleted")
    void deletePatientRemovesPatient() {
        clinic.addPatient(new Patient("P001", "John Smith", "0412345678"));

        boolean deleted = clinic.deletePatient("P001");

        assertTrue(deleted);
        assertEquals(0, clinic.getPatients().size());
        assertNull(clinic.findPatientByID("P001"));
    }

    @Test
    @DisplayName("Deleting a patient that does not exist returns false")
    void deletePatientReturnsFalseWhenMissing() {
        assertFalse(clinic.deletePatient("P999"));
    }

    @Test
    @DisplayName("Patients saved to file are restored when loaded back")
    void saveAndLoadRestoresPatients() throws IOException {
        clinic.addPatient(new Patient("P001", "John Smith", "0412345678"));
        clinic.addPatient(new Patient("P002", "Sarah Lee", "0423456789"));
        clinic.saveData();

        Clinic reloaded = new Clinic(tempDir.toString());
        reloaded.loadData();

        assertEquals(2, reloaded.getPatients().size());
        assertEquals("John Smith", reloaded.findPatientByID("P001").getPatientName());
        assertEquals("0423456789", reloaded.findPatientByID("P002").getPhoneNumber());
    }
}
