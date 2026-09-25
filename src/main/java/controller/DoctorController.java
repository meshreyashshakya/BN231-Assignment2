package controller;

import model.Clinic;
import model.Doctor;
import view.RegisterDoctorView;

import javax.swing.JOptionPane;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoctorController {

    private Clinic clinic;
    private Frame parentFrame;

    public DoctorController(Clinic clinic, Frame parentFrame) {
        this.clinic = clinic;
        this.parentFrame = parentFrame;
    }

    public void openRegisterDoctor() {
        RegisterDoctorView view = new RegisterDoctorView(parentFrame);

        view.getRegisterButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerDoctor(view);
            }
        });

        view.getCancelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });

        view.setVisible(true);
    }

    private void registerDoctor(RegisterDoctorView view) {

        String doctorID = view.getDoctorID().trim();
        String doctorName = view.getDoctorName().trim();
        String speciality = view.getSpeciality().trim();

        if (doctorID.isEmpty() || doctorName.isEmpty() || speciality.isEmpty()) {
            showError(view, "All fields are required.");
            return;
        }

        if (!doctorID.matches("D\\d{3}")) {
            showError(view, "Doctor ID must be in the format D001.");
            return;
        }

        if (!doctorName.matches("[a-zA-Z ]+")) {
            showError(view, "Doctor name must contain letters only.");
            return;
        }

        if (!speciality.matches("[a-zA-Z ]+")) {
            showError(view, "Speciality must contain letters only.");
            return;
        }

        Doctor doctor = new Doctor(doctorID, doctorName, speciality);
        boolean added = clinic.addDoctor(doctor);

        if (!added) {
            showError(view, "A doctor with ID " + doctorID + " already exists.");
            return;
        }

        JOptionPane.showMessageDialog(
            view,
            "Doctor registered successfully.\n"
                + "ID: " + doctor.getDoctorID() + "\n"
                + "Name: " + doctor.getDoctorName() + "\n"
                + "Speciality: " + doctor.getDoctorSpeciality(),
            "Registration Successful",
            JOptionPane.INFORMATION_MESSAGE
        );
        view.clearFields();
    }

    private void showError(java.awt.Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Validation Error", JOptionPane.ERROR_MESSAGE);
    }
}
