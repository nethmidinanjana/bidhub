package lk.nd.bidhub.beans;

import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import lk.nd.bidhub.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Singleton
@Startup
public class UserManagerBean {

    private final List<User> userList = Collections.synchronizedList(new ArrayList<>());

    //register user
    public boolean registerUser(User user){
        if(!emailExists(user.getEmail())){
            userList.add(user);
            return true;
        }

        return false;
    }

    //check if email already exists
    public boolean emailExists(String email){
        for(User user: userList){
            if(user.getEmail().equalsIgnoreCase(email)){
                return true;
            }
        }

        return false;
    }

    //login authentication
    public User authenticate(String email, String password){
        for(User user: userList){
            if(user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)){
                return user;
            }
        }

        return null;
    }
}
