package view;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;

public class RegisterDoctorView extends JDialog {

    private JTextField doctorIDField;
    private JTextField doctorNameField;
    private JTextField specialityField;
    private JButton registerButton;
    private JButton cancelButton;

    public RegisterDoctorView(Frame owner) {
        super(owner, "Register Doctor", true);
        setSize(420, 260);
        setLocationRelativeTo(owner);

        JLabel heading = new JLabel("Register New Doctor", JLabel.CENTER);
        heading.setFont(new Font("SansSerif", Font.BOLD, 16));
        heading.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));

        doctorIDField = new JTextField();
        doctorNameField = new JTextField();
        specialityField = new JTextField();

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        formPanel.add(new JLabel("Doctor ID:"));
        formPanel.add(doctorIDField);
        formPanel.add(new JLabel("Doctor Name:"));
        formPanel.add(doctorNameField);
        formPanel.add(new JLabel("Speciality:"));
        formPanel.add(specialityField);

        registerButton = new JButton("Register Doctor");
        cancelButton = new JButton("Cancel");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 15, 10));
        buttonPanel.add(registerButton);
        buttonPanel.add(cancelButton);

        add(heading, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public String getDoctorID() {
        return doctorIDField.getText();
    }

    public String getDoctorName() {
        return doctorNameField.getText();
    }

    public String getSpeciality() {
        return specialityField.getText();
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void clearFields() {
        doctorIDField.setText("");
        doctorNameField.setText("");
        specialityField.setText("");
        doctorIDField.requestFocus();
    }
}
