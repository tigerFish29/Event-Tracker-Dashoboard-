package com.eventtracker.events.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.eventtracker.events.model.UsersDTO;
import com.eventtracker.events.service.UsersService;

@Controller
public class UsersController {

    private final UsersService usersService;

    // constructor 
    public UsersController(final UsersService usersService) {
        this.usersService = usersService;
    }

    // crud paths for users 
    @GetMapping("/")
    public String home() {
        return "hello welcome to the house ";
    }

    // all users 
    @RequestMapping(path="/userss", method = RequestMethod.GET)
    public String find_All(Model model) {
        List<UsersDTO> users = usersService.findAll();
        model.addAttribute("users", users);
        model.addAttribute("usersDTO", new UsersDTO());
        return "users";
    }

    @RequestMapping(path="/userss/{id}", method = RequestMethod.GET)
    public String getUser(Model model, @PathVariable("id") Long id) {
        UsersDTO usersDTO = usersService.get(id);
        model.addAttribute("usersDTO",  usersDTO);
        return "edit";
    }

    

}