package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PatientTest {

    private Patient patient;

    @BeforeEach
    void setUp() {
        patient = new Patient("P001", "John Smith", "0412345678");
    }

    @Test
    @DisplayName("Constructor stores all three values correctly")
    void constructorStoresAllValues() {
        assertEquals("P001", patient.getPatientID());
        assertEquals("John Smith", patient.getPatientName());
        assertEquals("0412345678", patient.getPhoneNumber());
    }

    @Test
    @DisplayName("Setting a new name updates the patient name")
    void setPatientNameUpdatesName() {
        patient.setPatientName("John Smithson");
        assertEquals("John Smithson", patient.getPatientName());
    }

    @Test
    @DisplayName("Setting a new phone number updates the phone number")
    void setPhoneNumberUpdatesPhone() {
        patient.setPhoneNumber("0499999999");
        assertEquals("0499999999", patient.getPhoneNumber());
    }

    @Test
    @DisplayName("Changing the name does not change the patient ID")
    void updatingNameLeavesIdUnchanged() {
        patient.setPatientName("Different Name");
        assertEquals("P001", patient.getPatientID());
        assertNotEquals("Different Name", patient.getPatientID());
    }
}
