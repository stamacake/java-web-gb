package com.stamacake.mvc.services;

import com.stamacake.mvc.entities.User;
import com.stamacake.mvc.repositories.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component("userService")
public class UserService {

    @Autowired
    private UserRep userRep;

    public void delete(long id) {
        userRep.delete(id);
    }

    public User findUser(Long id){
        return userRep.findUser(id);
    }

    public void saveUser(User user){
        userRep.saveUser(user);
    }

    public List<User> getAllUsers(){
        return userRep.getAllUsers();
    }

}
