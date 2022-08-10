package com.eventtracker.events.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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
        UsersDTO usersDTO = usersService.get(id);
        model.addAttribute("usersDTO",  usersDTO);
        return null;
    }

    // create users {} 
    @RequestMapping(path="/create", method = RequestMethod.POST)
    public RedirectView createUser(RedirectAttributes redirects, @ModelAttribute UsersDTO usersDTO) {
        usersService.create(usersDTO);
        String message = "Created user <b>" + usersDTO.getFirstName() + "  " + usersDTO.getLastName() + "</b> .";
        //RedirectView redirectView = new RedirectView("/create", true);
        redirects.addFlashAttribute("userMessage", message);
        return null;
    }

    // update the user 
    @RequestMapping(path="/edit/{id}", method = RequestMethod.POST)
    public RedirectView updateUser(RedirectAttributes redirects, @PathVariable("id") Long id, @ModelAttribute UsersDTO usersDTO) {
      usersService.update(id, usersDTO);
      String message = "Updated the user <b>" + usersDTO.getFirstName() + "  " + usersDTO.getLastName() + "</b> .";
      //RedirectView redirectedView = new RedirectView("/", true);
      redirects.addFlashAttribute("userUpdated", message);
      return null;

    }

    @RequestMapping(path="/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        this.usersService.delete(id);
        redirectAttributes.addFlashAttribute("message", "User has now been deleted!");
        return null;
    }

    
    

}