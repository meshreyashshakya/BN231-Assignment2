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

public class RegisterPatientView extends JDialog {

    private JTextField patientIDField;
    private JTextField patientNameField;
    private JTextField phoneNumberField;
    private JButton registerButton;
    private JButton cancelButton;

    public RegisterPatientView(Frame owner) {
        super(owner, "Register Patient", true);
        setSize(420, 260);
        setLocationRelativeTo(owner);

        JLabel heading = new JLabel("Register New Patient", JLabel.CENTER);
        heading.setFont(new Font("SansSerif", Font.BOLD, 16));
        heading.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));

        patientIDField = new JTextField();
        patientNameField = new JTextField();
        phoneNumberField = new JTextField();

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        formPanel.add(new JLabel("Patient ID:"));
        formPanel.add(patientIDField);
        formPanel.add(new JLabel("Patient Name:"));
        formPanel.add(patientNameField);
        formPanel.add(new JLabel("Phone Number:"));
        formPanel.add(phoneNumberField);

        registerButton = new JButton("Register");
        cancelButton = new JButton("Cancel");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 15, 10));
        buttonPanel.add(registerButton);
        buttonPanel.add(cancelButton);

        add(heading, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public String getPatientIDInput() {
        return patientIDField.getText().trim();
    }

    public String getPatientNameInput() {
        return patientNameField.getText().trim();
    }

    public String getPhoneNumberInput() {
        return phoneNumberField.getText().trim();
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void clearFields() {
        patientIDField.setText("");
        patientNameField.setText("");
        phoneNumberField.setText("");
        patientIDField.requestFocus();
    }
}
