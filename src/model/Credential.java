package model;

public class Credential {
//    private List<String> passwordHistory;//To store all passwords, so that the new password doesn't repeat the last three passwords.
    private String password;

    public Credential(String password){
//        this.passwordHistory = passwordHistory;
        this.password = password;
    }

    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }


}
