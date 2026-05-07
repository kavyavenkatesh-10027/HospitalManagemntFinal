package service;

import model.*;
import repository.*;
import utils.*;

import java.time.LocalDate;
import java.util.Scanner;

public class AuthenticationService {

    private final Scanner scan;

    public AuthenticationService(Scanner scan){
        this.scan = scan;
    }

    //  LOGIN
    public User login() {

        System.out.println("Enter your registered email address:");
        String email = scan.nextLine().trim();

        User user = UserRepo.findUserByEmail(email);

        if (user == null) {
            System.out.println("Unregistered email. Would you like to sign up? Y/N");
            String choice = scan.nextLine().trim();

            if (choice.equalsIgnoreCase("Y")) {
                return signUp();
            }
            return null;
        }

        for (int attempts = 0; attempts < 3; attempts++) {

            System.out.println("Enter password:");
            String inputPassword = scan.next();

            if (UserRepo.validateCredentials(email, inputPassword)) {
                System.out.println("\nWelcome to Sugah Hospital!");
                System.out.println("Your Health Is Our Top Priority\n");
                return user;
            } else {
                System.out.println("Incorrect Password");
            }
        }

        System.out.println("Too many failed attempts.");
        return null;
    }


    public User signUp() {

        //  Email
        String email;
        while (true) {
            email = InputUtil.askValidNext(
                    "Enter a valid email address:",
                    "Invalid email format",
                    Validator::emailValidator
            );

            if (UserRepo.getEmailUserStore().isEmpty()==false && UserRepo.getEmailUserStore().containsKey(email)) {
                System.out.println("Email already exists");
            } else break;
        }

        //  Password
        String pswd;
        while (true) {
            pswd = InputUtil.askValidNext(
                    "Create a password:",
                    "Enter a valid password",
                    Validator::passwordValidator
            );

            String confirm = InputUtil.askValidNext(
                    "Re-enter password:",
                    "Passwords do not match",
                    input -> Validator.confirmPasswordValidator(pswd, input)
            );

            break;
        }

        scan.nextLine(); // buffer clear

        // ✅ Basic details
        String name = InputUtil.ask("Enter your full name:");

        String phnNo = InputUtil.askValidNext(
                "Enter your contact no:",
                "Enter a valid contact no:",
                Validator::phnNoValidator
        );

        String gender = InputUtil.askValidNext(
                "Enter gender: F/M/O",
                "Choose from F/M/O",
                g -> Validator.genderValidator(g.toUpperCase())
        ).toUpperCase();

        // ✅ DOB
        LocalDate dob;
        while (true) {
            try {
                System.out.println("Enter DOB (yyyy-mm-dd):");
                dob = LocalDate.parse(scan.next());
                if (Validator.dobValidator(dob)) break;
                System.out.println("Invalid age");
            } catch (Exception e) {
                System.out.println("Invalid date format");
            }
        }

        // ✅ Blood group
        String choice = InputUtil.askValidNext(

                "Select Blood Group:\n1.O+ 2.O- 3.A+ 4.A- 5.B+ 6.B- 7.AB+ 8.AB-",
                "Invalid choice",
                s -> s.matches("[1-8]")
        );

        Patient.BloodGroup bloodGroup = null;

        switch (choice) {
            case "1":
                bloodGroup = Patient.BloodGroup.O_positive;
                break;
            case "2":
                bloodGroup = Patient.BloodGroup.O_negative;
                break;
            case "3":
                bloodGroup = Patient.BloodGroup.A_positive;
                break;
            case "4":
                bloodGroup = Patient.BloodGroup.A_negative;
                break;
            case "5":
                bloodGroup = Patient.BloodGroup.B_positive;
                break;
            case "6":
                bloodGroup = Patient.BloodGroup.B_negative;
                break;
            case "7":
                bloodGroup = Patient.BloodGroup.AB_positive;
                break;
            case "8":
                bloodGroup = Patient.BloodGroup.AB_negative;
                break;
        }

        scan.nextLine();

        // ✅ Family details
        String father = InputUtil.ask("Enter name of father:");
        String mother = InputUtil.ask("Enter name of mother:");

        String guardian = InputUtil.askValid(
                "Enter name of guardian (mandatory):",
                "Cannot be empty",
                Validator::notNullValidator
        );

        String guardianNumber = InputUtil.askValidNext(
                "Enter guardian contact number:",
                "Enter a valid contact number:",
                Validator::phnNoValidator
        );

        // ✅ Create user
        Patient newPatient = new Patient(name, gender, dob, phnNo, email, bloodGroup, father, mother, guardian, guardianNumber);
        UserRepo.saveUser(newPatient, pswd);
        PatientRepository.addPatient(newPatient);

        System.out.println("\nUser created successfully!");
        return (User)newPatient;
    }

    public void exit() {
        System.out.println("Thank you for using Sugah Hospital App");
        System.exit(0);
    }


}