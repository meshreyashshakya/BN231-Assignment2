package controller;

import model.Clinic;
import view.MainMenuView;

import javax.swing.JOptionPane;
import java.io.IOException;

public class MainMenuController {

    private Clinic clinic;
    private MainMenuView view;
    private PatientController patientController;

    public MainMenuController(Clinic clinic, MainMenuView view) {
        this.clinic = clinic;
        this.view = view;
        this.patientController = new PatientController(clinic, view);
        attachListeners();
    }

    private void attachListeners() {
        view.getRegisterPatientButton().addActionListener(event -> patientController.openRegisterPatient());
        view.getSearchPatientButton().addActionListener(event -> patientController.openSearchPatient());
        view.getSaveDataButton().addActionListener(event -> handleSave());
        view.getLoadDataButton().addActionListener(event -> handleLoad());
        view.getExitButton().addActionListener(event -> handleExit());
    }

    private void handleSave() {
        try {
            clinic.saveData();
            JOptionPane.showMessageDialog(view,
                    clinic.getPatients().size() + " patient record(s) saved to file.",
                    "Save Successful",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(view,
                    "Could not save data to file: " + e.getMessage(),
                    "Save Failed",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleLoad() {
        try {
            clinic.loadData();
            JOptionPane.showMessageDialog(view,
                    clinic.getPatients().size() + " patient record(s) loaded from file.",
                    "Load Successful",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(view,
                    "Could not load data from file: " + e.getMessage(),
                    "Load Failed",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleExit() {
        int choice = JOptionPane.showConfirmDialog(view,
                "Do you want to save your data before exiting?",
                "Exit",
                JOptionPane.YES_NO_CANCEL_OPTION);

        if (choice == JOptionPane.CANCEL_OPTION) {
            return;
        }
        if (choice == JOptionPane.YES_OPTION) {
            handleSave();
        }
        System.exit(0);
    }
}
