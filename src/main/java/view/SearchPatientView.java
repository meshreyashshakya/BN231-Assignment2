package view;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;

public class SearchPatientView extends JDialog {

    private JTextField searchField;
    private JButton searchButton;
    private JButton showAllButton;
    private JButton closeButton;
    private JTable resultTable;
    private DefaultTableModel tableModel;

    public SearchPatientView(Frame owner) {
        super(owner, "Search Patient", true);
        setSize(560, 380);
        setLocationRelativeTo(owner);

        JLabel heading = new JLabel("Search Patient by ID", JLabel.CENTER);
        heading.setFont(new Font("SansSerif", Font.BOLD, 16));
        heading.setBorder(BorderFactory.createEmptyBorder(15, 10, 5, 10));

        searchField = new JTextField(15);
        searchButton = new JButton("Search");
        showAllButton = new JButton("Show All");

        JPanel searchPanel = new JPanel();
        searchPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        searchPanel.add(new JLabel("Patient ID:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(showAllButton);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(heading, BorderLayout.NORTH);
        topPanel.add(searchPanel, BorderLayout.CENTER);

        String[] columns = {"Patient ID", "Patient Name", "Phone Number"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        resultTable = new JTable(tableModel);
        resultTable.setRowHeight(24);

        JScrollPane scrollPane = new JScrollPane(resultTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        closeButton = new JButton("Close");
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        bottomPanel.add(closeButton);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public String getSearchInput() {
        return searchField.getText().trim();
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JButton getShowAllButton() {
        return showAllButton;
    }

    public JButton getCloseButton() {
        return closeButton;
    }

    public void clearResults() {
        tableModel.setRowCount(0);
    }

    public void addResultRow(String patientID, String patientName, String phoneNumber) {
        tableModel.addRow(new Object[]{patientID, patientName, phoneNumber});
    }
}
