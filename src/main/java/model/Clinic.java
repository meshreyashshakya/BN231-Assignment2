package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Clinic {

    private static final String DEFAULT_DATA_FOLDER = "data";

    private ArrayList<Patient> patients;
    private ArrayList<Doctor> doctors;
    private String dataFolder;

    public Clinic() {
        this(DEFAULT_DATA_FOLDER);
    }

    public Clinic(String dataFolder) {
        this.patients = new ArrayList<>();
        this.doctors = new ArrayList<>();
        this.dataFolder = dataFolder;
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public ArrayList<Doctor> getDoctors() {
        return doctors;
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

    public boolean addDoctor(Doctor doctor) {
        if (doctor == null) {
            return false;
        }
        if (findDoctorByID(doctor.getDoctorID()) != null) {
            return false;
        }
        doctors.add(doctor);
        return true;
    }

    public Doctor findDoctorByID(String doctorID) {
        if (doctorID == null) {
            return null;
        }
        for (int i = 0; i < doctors.size(); i++) {
            Doctor current = doctors.get(i);
            if (current.getDoctorID().equalsIgnoreCase(doctorID.trim())) {
                return current;
            }
        }
        return null;
    }

    public boolean deleteDoctor(String doctorID) {
        Doctor doctor = findDoctorByID(doctorID);
        if (doctor == null) {
            return false;
        }
        doctors.remove(doctor);
        return true;
    }

    public void saveData() throws IOException {
        File folder = new File(dataFolder);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(patientFilePath()))) {
            for (Patient patient : patients) {
                writer.write(patient.getPatientID() + ","
                        + patient.getPatientName() + ","
                        + patient.getPhoneNumber());
                writer.newLine();
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(doctorFilePath()))) {
            for (Doctor doctor : doctors) {
                writer.write(doctor.getDoctorID() + ","
                        + doctor.getDoctorName() + ","
                        + doctor.getDoctorSpeciality());
                writer.newLine();
            }
        }
    }

    public void loadData() throws IOException {
        loadPatients();
        loadDoctors();
    }

    private void loadPatients() throws IOException {
        File file = new File(patientFilePath());
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

    private void loadDoctors() throws IOException {
        File file = new File(doctorFilePath());
        if (!file.exists()) {
            return;
        }
        doctors.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    doctors.add(new Doctor(parts[0], parts[1], parts[2]));
                }
            }
        }
    }

    private String patientFilePath() {
        return dataFolder + File.separator + "patients.txt";
    }

    private String doctorFilePath() {
        return dataFolder + File.separator + "doctors.txt";
    }
}
