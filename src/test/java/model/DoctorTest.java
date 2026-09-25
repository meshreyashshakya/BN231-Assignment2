package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DoctorTest {

    @Test
    public void testDoctorCreation() {
        Doctor doctor = new Doctor("D001", "John Smith", "Cardiology");

        assertEquals("D001", doctor.getDoctorID());
        assertEquals("John Smith", doctor.getDoctorName());
        assertEquals("Cardiology", doctor.getDoctorSpeciality());
    }

    @Test
    public void testSetSpecialityUpdatesSpeciality() {
        Doctor doctor = new Doctor("D001", "John Smith", "Cardiology");
        doctor.setDoctorSpeciality("Neurology");

        assertEquals("Neurology", doctor.getDoctorSpeciality());
        assertEquals("D001", doctor.getDoctorID());
    }

    @Test
    public void testClinicStoresDoctor() {
        Clinic clinic = new Clinic();
        boolean added = clinic.addDoctor(new Doctor("D001", "John Smith", "Cardiology"));

        assertTrue(added);
        assertEquals(1, clinic.getDoctors().size());
    }

    @Test
    public void testDuplicateDoctorIdIsRejected() {
        Clinic clinic = new Clinic();
        clinic.addDoctor(new Doctor("D001", "John Smith", "Cardiology"));
        boolean added = clinic.addDoctor(new Doctor("D001", "Someone Else", "Neurology"));

        assertFalse(added);
        assertEquals(1, clinic.getDoctors().size());
    }

    @Test
    public void testFindDoctorByID() {
        Clinic clinic = new Clinic();
        clinic.addDoctor(new Doctor("D001", "John Smith", "Cardiology"));
        clinic.addDoctor(new Doctor("D002", "Mary Jones", "Paediatrics"));

        Doctor found = clinic.findDoctorByID("D002");

        assertNotNull(found);
        assertEquals("Mary Jones", found.getDoctorName());
        assertNull(clinic.findDoctorByID("D999"));
    }
}
