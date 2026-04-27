package repository;


import java.util.HashMap;

import model.*;

public class UserRepo {
    private static HashMap<String, User> emailUserStore;
    private static HashMap<String, Credential> identityCredentialStore;

    public static HashMap<String, User> getEmailUserStore() {
        return emailUserStore;
    }

    public static User findUserByEmail(String email){
        return emailUserStore.get(email);
    }

    public static void saveUser(User user, String password){
        if((emailUserStore.get(user.getEmailId())!=null)){
            emailUserStore.put(user.getEmailId(), user);
            identityCredentialStore.put(user.getId(), new Credential(password));
        }
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
            identityCredentialStore.put(email, new Credential(newPassword));
        }
        return true;
    }

    public static boolean deleteCredential(String email, String password){
        Credential credential = identityCredentialStore.get(email);

        if (!credential.validatePassword(password)) {
            return false;
        }else {
            identityCredentialStore.remove(email);
        }
        return true;
    }
}
/*
package course_manager.repository;

import course_manager.model.User;
import course_manager.model.Credential;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UserRepo {
    private static final Map<String, User> userStore = new HashMap<>();
    private static final Map<String, Credential> credentialStore = new HashMap<>();


    public User findUserByEmail(String email){
        return userStore.get(email);
    }

    public void saveUser(User user, String password){
        userStore.put(user.getEmail(), user);
        credentialStore.put(user.getId(), new Credential(password));
    }

    public boolean validateCredentials(String email, String password){
        User user = findUserByEmail(email);
        if(user == null){
            return false;
        }
        Credential credential = credentialStore.get(user.getId());
        return  credential != null && credential.validatePassword(password);
    }
}

 */