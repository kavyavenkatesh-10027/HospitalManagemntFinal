package repository;


import java.util.HashMap;

import model.*;

public class UserRepo {
    private static HashMap<String, User> emailUserStore = new HashMap<>();
    private static HashMap<String, Credential> identityCredentialStore= new HashMap<>();

    public static HashMap<String, User> getEmailUserStore() {
        return new HashMap<>(emailUserStore);
    }

    public static User findUserByEmail(String email){
            return emailUserStore.get(email);
    }

    public static void saveUser(User user, String password){
//        if((emailUserStore.get(user.getEmailId())!=null)){
        emailUserStore.put(user.getEmailId(), user);
        identityCredentialStore.put(user.getId(), new Credential(password));
        System.out.println("stored successfully");
        System.out.println("EMAIL STORE: " + emailUserStore);
        System.out.println("CREDENTIAL STORE: " + identityCredentialStore);
//        }
    }

    public static boolean validateCredentials(String email, String password){
        User user = findUserByEmail(email);
        if(user == null){
            return false;
        }
        Credential credential = identityCredentialStore.get(user.getId());
        return  credential != null && credential.validatePassword(password);
    }

    public static boolean updatePassword(String email, String oldPassword, String newPassword){

        Credential credential = identityCredentialStore.get(findUserByEmail(email).getId());

        if (!credential.validatePassword(oldPassword)) {
            return false;
        }else {
            identityCredentialStore.put(findUserByEmail(email).getId(), new Credential(newPassword));
        }
        return true;
    }

    public static boolean deleteCredential(String email, String password){
        Credential credential = identityCredentialStore.get(findUserByEmail(email).getId());

        if (!credential.validatePassword(password)) {
            return false;
        }else {
            identityCredentialStore.remove(findUserByEmail(email).getId());
        }
        return true;
    }
}


