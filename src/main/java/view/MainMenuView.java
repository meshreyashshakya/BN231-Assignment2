package view;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

public class MainMenuView extends JFrame {

    private JButton registerPatientButton;
    private JButton registerDoctorButton;
    private JButton searchPatientButton;
    private JButton saveDataButton;
    private JButton loadDataButton;
    private JButton exitButton;

    public MainMenuView() {
        setTitle("Community Health Clinic Management System");
        setSize(460, 470);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel heading = new JLabel("Community Health Clinic", JLabel.CENTER);
        heading.setFont(new Font("SansSerif", Font.BOLD, 20));
        heading.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        JLabel subHeading = new JLabel("Main Menu", JLabel.CENTER);
        subHeading.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JPanel headingPanel = new JPanel(new BorderLayout());
        headingPanel.add(heading, BorderLayout.CENTER);
        headingPanel.add(subHeading, BorderLayout.SOUTH);

        registerPatientButton = createMenuButton("Register Patient");
        registerDoctorButton = createMenuButton("Register Doctor");
        searchPatientButton = createMenuButton("Search Patient");
        saveDataButton = createMenuButton("Save Data to File");
        loadDataButton = createMenuButton("Load Data from File");
        exitButton = createMenuButton("Exit");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 30, 60));

        buttonPanel.add(registerPatientButton);
        buttonPanel.add(Box.createVerticalStrut(12));
        buttonPanel.add(registerDoctorButton);
        buttonPanel.add(Box.createVerticalStrut(12));
        buttonPanel.add(searchPatientButton);
        buttonPanel.add(Box.createVerticalStrut(12));
        buttonPanel.add(saveDataButton);
        buttonPanel.add(Box.createVerticalStrut(12));
        buttonPanel.add(loadDataButton);
        buttonPanel.add(Box.createVerticalStrut(12));
        buttonPanel.add(exitButton);

        add(headingPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        button.setFocusPainted(false);
        return button;
    }

    public JButton getRegisterPatientButton() {
        return registerPatientButton;
    }

    public JButton getRegisterDoctorButton() {
        return registerDoctorButton;
    }

    public JButton getSearchPatientButton() {
        return searchPatientButton;
    }

    public JButton getSaveDataButton() {
        return saveDataButton;
    }

    public JButton getLoadDataButton() {
        return loadDataButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }
}
