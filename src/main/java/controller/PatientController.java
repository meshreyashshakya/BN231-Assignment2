package controller;

import model.Clinic;
import model.Patient;
import view.RegisterPatientView;
import util.PatientSorter;
import view.SearchPatientView;

import javax.swing.JOptionPane;
import java.awt.Frame;
import java.util.ArrayList;

public class PatientController {

    private Clinic clinic;
    private Frame parentFrame;

    public PatientController(Clinic clinic, Frame parentFrame) {
        this.clinic = clinic;
        this.parentFrame = parentFrame;
    }

    public void openRegisterPatient() {
        RegisterPatientView view = new RegisterPatientView(parentFrame);
        view.getRegisterButton().addActionListener(event -> handleRegister(view));
        view.getCancelButton().addActionListener(event -> view.dispose());
        view.setVisible(true);
    }

    public void openSearchPatient() {
        SearchPatientView view = new SearchPatientView(parentFrame);
        view.getSearchButton().addActionListener(event -> handleSearch(view));
        view.getShowAllButton().addActionListener(event -> showAllPatients(view));
        view.getSortByNameButton().addActionListener(event -> showSortedPatients(view));
        view.getCloseButton().addActionListener(event -> view.dispose());
        showAllPatients(view);
        view.setVisible(true);
    }

    private void handleRegister(RegisterPatientView view) {
        String patientID = view.getPatientIDInput();
        String patientName = view.getPatientNameInput();
        String phoneNumber = view.getPhoneNumberInput();

        String error = validatePatientInput(patientID, patientName, phoneNumber);
        if (error != null) {
            showError(view, error);
            return;
        }

        Patient patient = new Patient(patientID, patientName, phoneNumber);
        boolean added = clinic.addPatient(patient);

        if (!added) {
            showError(view, "A patient with ID " + patientID + " already exists.");
            return;
        }

        JOptionPane.showMessageDialog(view,
                "Patient " + patientName + " registered successfully.",
                "Registration Successful",
                JOptionPane.INFORMATION_MESSAGE);
        view.clearFields();
    }

    private String validatePatientInput(String patientID, String patientName, String phoneNumber) {
        if (patientID.isEmpty() || patientName.isEmpty() || phoneNumber.isEmpty()) {
            return "All fields are required. Please complete every field.";
        }
        if (!patientID.matches("[A-Za-z0-9]+")) {
            return "Patient ID must contain letters and numbers only.";
        }
        if (!patientName.matches("[A-Za-z ]+")) {
            return "Patient name must contain letters and spaces only.";
        }
        if (!phoneNumber.matches("\\d{10}")) {
            return "Phone number must be exactly 10 digits.";
        }
        return null;
    }

    private void handleSearch(SearchPatientView view) {
        String patientID = view.getSearchInput();
        view.clearResults();

        if (patientID.isEmpty()) {
            showError(view, "Please enter a patient ID to search.");
            return;
        }

        Patient found = clinic.findPatientByID(patientID);

        if (found == null) {
            JOptionPane.showMessageDialog(view,
                    "No patient found with ID " + patientID + ".",
                    "Not Found",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        view.addResultRow(found.getPatientID(), found.getPatientName(), found.getPhoneNumber());
    }

    private void showAllPatients(SearchPatientView view) {
        view.clearResults();
        ArrayList<Patient> patients = clinic.getPatients();
        for (Patient patient : patients) {
            view.addResultRow(patient.getPatientID(), patient.getPatientName(), patient.getPhoneNumber());
        }
    }

    private void showSortedPatients(SearchPatientView view) {
        view.clearResults();
        ArrayList<Patient> sorted = PatientSorter.sortByName(clinic.getPatients());
        for (Patient patient : sorted) {
            view.addResultRow(patient.getPatientID(), patient.getPatientName(), patient.getPhoneNumber());
        }
    }

    private void showError(java.awt.Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Invalid Input", JOptionPane.ERROR_MESSAGE);
    }
}
