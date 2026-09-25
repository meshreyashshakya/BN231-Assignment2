package controller;

import model.Clinic;
import model.Patient;
import view.RegisterPatientView;
import util.PatientSorter;
import view.SearchPatientView;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridLayout;
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
        view.getUpdateButton().addActionListener(event -> handleUpdate(view));
        view.getDeleteButton().addActionListener(event -> handleDelete(view));
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

    private void handleUpdate(SearchPatientView view) {
        String patientID = view.getSelectedPatientID();
        if (patientID == null) {
            showError(view, "Select a patient row in the table first.");
            return;
        }

        JTextField nameField = new JTextField(view.getSelectedPatientName());
        JTextField phoneField = new JTextField(view.getSelectedPhoneNumber());

        JPanel form = new JPanel(new GridLayout(2, 2, 8, 8));
        form.add(new javax.swing.JLabel("Patient Name:"));
        form.add(nameField);
        form.add(new javax.swing.JLabel("Phone Number:"));
        form.add(phoneField);

        int choice = JOptionPane.showConfirmDialog(view, form,
                "Update Patient " + patientID, JOptionPane.OK_CANCEL_OPTION);

        if (choice != JOptionPane.OK_OPTION) {
            return;
        }

        String newName = nameField.getText().trim();
        String newPhone = phoneField.getText().trim();

        if (newName.isEmpty() || newPhone.isEmpty()) {
            showError(view, "Name and phone number are both required.");
            return;
        }
        if (!newName.matches("[A-Za-z ]+")) {
            showError(view, "Patient name must contain letters and spaces only.");
            return;
        }
        if (!newPhone.matches("\\d{10}")) {
            showError(view, "Phone number must be exactly 10 digits.");
            return;
        }

        boolean updated = clinic.updatePatient(patientID, newName, newPhone);

        if (!updated) {
            showError(view, "Patient " + patientID + " could not be found.");
            return;
        }

        JOptionPane.showMessageDialog(view,
                "Patient " + patientID + " updated successfully.",
                "Update Successful",
                JOptionPane.INFORMATION_MESSAGE);
        showAllPatients(view);
    }

    private void handleDelete(SearchPatientView view) {
        String patientID = view.getSelectedPatientID();
        if (patientID == null) {
            showError(view, "Select a patient row in the table first.");
            return;
        }

        int choice = JOptionPane.showConfirmDialog(view,
                "Delete patient " + patientID + " (" + view.getSelectedPatientName() + ")?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);

        if (choice != JOptionPane.YES_OPTION) {
            return;
        }

        boolean deleted = clinic.deletePatient(patientID);

        if (!deleted) {
            showError(view, "Patient " + patientID + " could not be found.");
            return;
        }

        JOptionPane.showMessageDialog(view,
                "Patient " + patientID + " deleted. Remember to save.",
                "Delete Successful",
                JOptionPane.INFORMATION_MESSAGE);
        showAllPatients(view);
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
