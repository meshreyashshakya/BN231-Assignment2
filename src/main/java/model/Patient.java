package model;

public class Patient {

    private String patientID;
    private String patientName;
    private String phoneNumber;

    public Patient(String patientID, String patientName, String phoneNumber) {
        this.patientID = patientID;
        this.patientName = patientName;
        this.phoneNumber = phoneNumber;
    }

    public String getPatientID() {
        return patientID;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
