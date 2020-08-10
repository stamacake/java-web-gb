package com.stamacake.mvc.Constroller;

import com.stamacake.mvc.entities.User;
import com.stamacake.mvc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    public void setService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "allusers";
    }

    @PostMapping("/add")
    public String addUser(@RequestParam String name, @RequestParam int age) {
        userService.saveUser(new User(name, age));
        return "redirect:/users/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable long id) {
        userService.delete(id);
        return "redirect:/users/all";
    }

    @GetMapping("/find")
    @ResponseBody
    public User findUser(@RequestParam long id) {
        return userService.findUser(id);
    }
}