package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.PatientSorter;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatientSorterTest {

    private ArrayList<Patient> unsorted;

    @BeforeEach
    void setUp() {
        unsorted = new ArrayList<>();
        unsorted.add(new Patient("P003", "David Chen", "0434567890"));
        unsorted.add(new Patient("P001", "Sarah Lee", "0423456789"));
        unsorted.add(new Patient("P002", "Anna Brown", "0412345678"));
    }

    @Test
    @DisplayName("Insertion sort orders patients alphabetically by name")
    void sortByNameOrdersAlphabetically() {
        ArrayList<Patient> sorted = PatientSorter.sortByName(unsorted);

        assertEquals("Anna Brown", sorted.get(0).getPatientName());
        assertEquals("David Chen", sorted.get(1).getPatientName());
        assertEquals("Sarah Lee", sorted.get(2).getPatientName());
    }

    @Test
    @DisplayName("Bubble sort orders patients by ID")
    void sortByIdOrdersAscending() {
        ArrayList<Patient> sorted = PatientSorter.sortByID(unsorted);

        assertEquals("P001", sorted.get(0).getPatientID());
        assertEquals("P002", sorted.get(1).getPatientID());
        assertEquals("P003", sorted.get(2).getPatientID());
    }

    @Test
    @DisplayName("Sorting returns a new list and leaves the original untouched")
    void sortDoesNotModifyOriginalList() {
        PatientSorter.sortByName(unsorted);
        assertEquals("David Chen", unsorted.get(0).getPatientName());
    }
}
