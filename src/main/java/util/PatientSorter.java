package util;

import model.Patient;

import java.util.ArrayList;

public class PatientSorter {

    public static ArrayList<Patient> sortByName(ArrayList<Patient> patients) {
        ArrayList<Patient> sorted = new ArrayList<>(patients);

        for (int i = 1; i < sorted.size(); i++) {
            Patient current = sorted.get(i);
            int j = i - 1;

            while (j >= 0 && sorted.get(j).getPatientName()
                    .compareToIgnoreCase(current.getPatientName()) > 0) {
                sorted.set(j + 1, sorted.get(j));
                j = j - 1;
            }
            sorted.set(j + 1, current);
        }
        return sorted;
    }

    public static ArrayList<Patient> sortByID(ArrayList<Patient> patients) {
        ArrayList<Patient> sorted = new ArrayList<>(patients);

        for (int i = 0; i < sorted.size() - 1; i++) {
            for (int j = 0; j < sorted.size() - 1 - i; j++) {
                Patient left = sorted.get(j);
                Patient right = sorted.get(j + 1);

                if (left.getPatientID().compareToIgnoreCase(right.getPatientID()) > 0) {
                    sorted.set(j, right);
                    sorted.set(j + 1, left);
                }
            }
        }
        return sorted;
    }
}
