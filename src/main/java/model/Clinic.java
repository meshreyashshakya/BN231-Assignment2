package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Clinic {

    private static final String PATIENT_FILE = "data/patients.txt";

    private ArrayList<Patient> patients;

    public Clinic() {
        patients = new ArrayList<>();
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public boolean addPatient(Patient patient) {
        if (patient == null) {
            return false;
        }
        if (findPatientByID(patient.getPatientID()) != null) {
            return false;
        }
        patients.add(patient);
        return true;
    }

    public Patient findPatientByID(String patientID) {
        if (patientID == null) {
            return null;
        }
        for (int i = 0; i < patients.size(); i++) {
            Patient current = patients.get(i);
            if (current.getPatientID().equalsIgnoreCase(patientID.trim())) {
                return current;
            }
        }
        return null;
    }

    public boolean updatePatient(String patientID, String newName, String newPhone) {
        Patient patient = findPatientByID(patientID);
        if (patient == null) {
            return false;
        }
        patient.setPatientName(newName);
        patient.setPhoneNumber(newPhone);
        return true;
    }

    public boolean deletePatient(String patientID) {
        Patient patient = findPatientByID(patientID);
        if (patient == null) {
            return false;
        }
        patients.remove(patient);
        return true;
    }

    public void saveData() throws IOException {
        File folder = new File("data");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATIENT_FILE))) {
            for (Patient patient : patients) {
                writer.write(patient.getPatientID() + ","
                        + patient.getPatientName() + ","
                        + patient.getPhoneNumber());
                writer.newLine();
            }
        }
    }

    public void loadData() throws IOException {
        File file = new File(PATIENT_FILE);
        if (!file.exists()) {
            return;
        }
        patients.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    patients.add(new Patient(parts[0], parts[1], parts[2]));
                }
            }
        }
    }
}
