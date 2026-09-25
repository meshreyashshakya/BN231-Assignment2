package model;

public class Doctor {

    private String doctorID;
    private String doctorName;
    private String doctorSpeciality;

    public Doctor(String doctorID, String doctorName, String doctorSpeciality) {
        this.doctorID = doctorID;
        this.doctorName = doctorName;
        this.doctorSpeciality = doctorSpeciality;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDoctorSpeciality() {
        return doctorSpeciality;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setDoctorSpeciality(String doctorSpeciality) {
        this.doctorSpeciality = doctorSpeciality;
    }
}
