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

import com.eventtracker.events.model.Users;
import com.eventtracker.events.service.UsersService;



@Controller
public class UsersController {
    
    private final UsersService service;

    public UsersController(UsersService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
       List<Users> users = service.getAllUsers();
       model.addAttribute("users", users);
       model.addAttribute("users", new Users());
       return null;
    }

    @GetMapping("/users/{id}")
    public String getUser(Model model, @PathVariable("id") Long id) {
        Users users = service.get(id);
        model.addAttribute("Users", users);
        return null;
    }

    @PostMapping("/create")
    public RedirectView createUser(RedirectAttributes redirects, @ModelAttribute Users user) {
        service.create(user);
        String message = "Created User <b>" + user.getFirstName() + " " + user.getLastName() + "</b>";
        redirects.addFlashAttribute("userMessage", message);
        return null;
    }


    @PostMapping("/update")
    public RedirectView updateUser(RedirectAttributes redirects, @PathVariable("id") Long id, @ModelAttribute Users user) {
        service.update(id, user);
        String message = "Updated User <b>" + user.getFirstName() + " " + user.getLastName() + "</b>";
        redirects.addFlashAttribute("userMessage", message);
        return null; 
    }

    @DeleteMapping("/users/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        return null;
    }

    
    
}
