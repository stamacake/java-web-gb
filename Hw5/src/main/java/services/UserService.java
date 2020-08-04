package services;

import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import repositories.UserRep;

import java.util.List;


@Component("userService")
public class UserService {

    @Autowired
    private UserRep userRep;


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
