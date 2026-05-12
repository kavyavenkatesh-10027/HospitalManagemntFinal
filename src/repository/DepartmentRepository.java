package repository;

import model.*;


import java.util.ArrayList;

import java.util.List;

public class DepartmentRepository {
    private final static List<Department> allDepartments= new ArrayList<>();


    public static List<Department> getAllDepartments() {
        return new ArrayList<>(allDepartments);
    }

    public static void addDepartment(String depName){
        allDepartments.add(new Department(depName));
    }



    public static Department findById(String departmentId) {
        for (Department d : allDepartments) {
            if (d.getDeptId().equals(departmentId)) {
                return d;
            }
        }
        return null;
    }

    public boolean equals(String val){
        if(this.toString().length()!=val.length()){
            return false;
        }
        for (int i = 0; i < val.length(); i++) {
            if(this.toString().charAt(i)!=val.charAt(i)){
                return false;
            }
        }
        return true;
    }
}
