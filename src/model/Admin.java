package model;

import utils.IDGenerator;

import java.time.LocalDate;

public class Admin extends User{
    private final String adminID;

    public Admin(String name, String gender, LocalDate dob, String phnNo, String emailId, Role role) {
        super(name, gender, dob, phnNo, emailId, role);
        this.adminID = IDGenerator.generateAdminId();
        role = Role.ADMIN;
    }

    @Override
    public String getId() {
        return adminID;
    }

    public String getAdminID() {
        return adminID;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminID='" + adminID + '\'' +
                '}';
    }
}
