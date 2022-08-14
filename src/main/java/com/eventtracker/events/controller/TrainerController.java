package com.eventtracker.events.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eventtracker.events.Repository.TrainerRepo;
import com.eventtracker.events.Service.TrainerService;
import com.eventtracker.events.model.Trainer;

@Controller
public class TrainerController {

    private final TrainerService service;
    private final TrainerRepo trainerRepo;

    public TrainerController(TrainerService service, TrainerRepo trainerRepo) {
        this.service = service;
        this.trainerRepo = trainerRepo;
    }

    /*
     *   CRUD METHODS 
    */

    @GetMapping("/trainers")
    public String getTrainers(Model model) {
        List<Trainer> trainers = service.getTrainers();
        model.addAttribute("trainers", trainers);
        model.addAttribute("trainer", new Trainer());
        return "trainers";
    }
    
    @PostMapping("/create")
    public String createTrainer(RedirectAttributes redirects, @ModelAttribute Trainer trainer) {
        service.createTrainer(trainer);
        return "home";
    }
   
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id ,Model model) {
        Trainer trainer = service.getTrainer(id);
        
        model.addAttribute("trainer", trainer);
        return "update-trainer";
    }

    @PostMapping("/update/{id}")
    public String updateTrainer(@PathVariable("id") Integer id, @Valid Trainer trainer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            trainer.setId(id);
            return "update-trainer";
        }
        return "trainers";
    }


    @GetMapping("/delete/{id}")
    public String deleteTrainer(@PathVariable("id") Integer id, Model model) {
        Trainer trainer = service.getTrainer(id);
        trainerRepo.delete(trainer);
        return "home";
    }

    
}
