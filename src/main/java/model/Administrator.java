package model;

public class Administrator {

    private String administratorID;
    private String administratorName;

    public Administrator(String administratorID, String administratorName) {
        this.administratorID = administratorID;
        this.administratorName = administratorName;
    }

    public String getAdministratorID() {
        return administratorID;
    }

    public String getAdministratorName() {
        return administratorName;
    }
}
