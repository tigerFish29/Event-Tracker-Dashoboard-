package com.eventtracker.events.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.eventtracker.events.Service.UserService;
import com.eventtracker.events.model.UserInfo;



@Controller
public class UserController {

    private final  UserService userService;

    // constructor 
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // home page 
   

    // crud paths 
    @GetMapping("/")
    public String getUsers(Model model) {
       List<UserInfo> users = userService.getUsers();
       model.addAttribute("users", users);
       model.addAttribute("userInfo", new UserInfo());
       return "users";
    }

    

    // crud path for one user {} 
    @GetMapping("/{id}")
    public String getUser(Model model, @PathVariable("id") Integer id) {
        UserInfo userInfo = userService.getUser(id);
        model.addAttribute("userInfo", userInfo);
        return "edit";
    }

    // crud path create [post ]
    @PostMapping("/")
    public String createUser(RedirectAttributes redirects, @ModelAttribute UserInfo userInfo) {
        userService.createUser(userInfo);
        return "home";  
        
    }

    // crud update 
    @PostMapping("/{id}")
    
    public RedirectView updateUser(RedirectAttributes redirects, @PathVariable("id") Integer id, @ModelAttribute UserInfo userInfo) {
        userService.updateUser(id, userInfo);
        String message = (userInfo.isActive() ? "Updated" : "Deleted") + "user <b>" + userInfo.getFirstName() + " " + userInfo.getLastName() + "</b> .";
        RedirectView redirectView = new RedirectView("/", true);
        redirects.addFlashAttribute("userMessage", message);
        return redirectView;
    }
     
    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable("id") Integer id) {
        return userService.removeUser(id);
    }

    
}

