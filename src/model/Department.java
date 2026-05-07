package model;

import utils.IDGenerator;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

public class Department {
    private final String deptId;
    private String deptName;
    private List<Doctor> doctorsUnderDepartment = new ArrayList<>();

    public Department(String deptName) {
        this.deptId = IDGenerator.generateDepartmentId();
        this.deptName = deptName;
    }

    public String getDeptId() {
        return deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public List<Doctor> getDoctorsUnderDepartment() {
        return doctorsUnderDepartment;
    }

    public void addNewDoctor(Doctor newDoctor){
        doctorsUnderDepartment.add(newDoctor);
    }

    public void removeDoctor(Doctor doctorToRemove){
        doctorsUnderDepartment.remove(doctorToRemove);
    }

    @Override
    public String toString() {
        return "Department{" +
                "deptId='" + deptId + '\'' +
                ", deptName='" + deptName + '\'' +
                ", doctorsUnderDepartment=" + doctorsUnderDepartment +
                '}';
    }
}
