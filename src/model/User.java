package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class User {
    private String name;
    private final String gender;
    private final LocalDate dob;
    private String phnNo;
    private String emailId;
    private final LocalDateTime joinedAt;
    private Role role;

    public User(String name, String gender, LocalDate dob, String phnNo, String emailId, Role role){
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.phnNo =phnNo;
        this.emailId = emailId;
        this.joinedAt = LocalDateTime.now();
    }

    public abstract String getId();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getPhnNo() {
        return phnNo;
    }

    public void setPhnNo(String phnNo) {
        this.phnNo = phnNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    public Role getRole() {
        return role;
    }

//    public void setRole(Role role) {
//        this.role = role;
//    }

     public enum Role {
        ADMIN,
        DOCTOR,
        NURSE,
        PATIENT,
        RECEPTIONIST
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", dob=" + dob +
                ", phnNo='" + phnNo + '\'' +
                ", emailId='" + emailId + '\'' +
                ", joinedAt=" + joinedAt +
                ", role=" + role +
                '}';
    }
}
