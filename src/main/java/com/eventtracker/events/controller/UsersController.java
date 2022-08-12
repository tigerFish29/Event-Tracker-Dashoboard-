package com.eventtracker.events.controller;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.eventtracker.events.domain.Users;
import com.eventtracker.events.service.UsersService;

@Controller
public class UsersController {

    private final UsersService usersService;

    // constructor 
    public UsersController(final UsersService usersService) {
        this.usersService = usersService;
    }

    // crud paths for users 
    @RequestMapping(path="/", method = RequestMethod.GET)
    public String home() {
        return "hello";
    }

    // all users 
    @RequestMapping(path="/userss", method = RequestMethod.GET)
    public String list(final Model model) {
        model.addAttribute("userss", usersService.findAll());
        return null;
    }

    @RequestMapping(path="/userss/{id}", method = RequestMethod.GET)
    public String getUser(Model model, @PathVariable("id") Long id) {
        Users users = usersService.get(id);
        model.addAttribute("users",  users);
        return null;
    }

    // create users {} 
    @PostMapping("/create")
    public RedirectView createUser(RedirectAttributes redirects, @ModelAttribute Users users) {
        usersService.create(users);
        String message = "Created user <b>" + users.getFirstName() + "  " + users.getLastName() + "</b> .";
        //RedirectView redirectView = new RedirectView("/create", true);
        redirects.addFlashAttribute("userMessage", message);
        return null;
    }

    // update the user 
    @RequestMapping(path="/edit/{id}", method = RequestMethod.POST)
    public RedirectView updateUser(RedirectAttributes redirects, @PathVariable("id") Long id, @ModelAttribute Users users) {
      usersService.update(id, users);
      String message = "Updated the user <b>" + users.getFirstName() + "  " + users.getLastName() + "</b> .";
      RedirectView redirectedView = new RedirectView("/", true);
      redirects.addFlashAttribute("userUpdated", message);
      return redirectedView;

    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) throws NotFoundException {
        usersService.delete(id);
        String message = "<b>User has now been deleted! </b>";
        redirectAttributes.addFlashAttribute("message", message);
        return "hello";
    }

}