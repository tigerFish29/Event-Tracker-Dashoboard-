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

import com.eventtracker.events.domain.CollectionEvents;
import com.eventtracker.events.service.CollectionEventsService;

@RequestMapping
@Controller

public class CollectionEventsController {

    private CollectionEventsService collectionEventsService;

    // constructor {} 
    public CollectionEventsController(final CollectionEventsService collectionEventsService) {
        this.collectionEventsService = collectionEventsService;
    }

    /**
     *    CRUD METHODS 
     */

     @RequestMapping(path = "/collections", method = RequestMethod.GET)
     public String list(final Model model) {
        model.addAttribute("collections", collectionEventsService.findAll());
        return null; // we dont have a template yet 
     }

     // get a single collection {}
     @RequestMapping(path = "/collection/{id}", method = RequestMethod.GET) 
        public String getCollection(Model model, @PathVariable("id") Long id) {
            CollectionEvents collectionEvents = collectionEventsService.get(id);
            model.addAttribute("collections", collectionEvents);
            return null; // we do not have a template 
        } 
    
    // create a collection {}  
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public RedirectView createCollection(RedirectAttributes redirectAttributes, @ModelAttribute CollectionEvents collectionEvents) {
        collectionEventsService.create(collectionEvents);
        RedirectView redirectView = new RedirectView("/", true);
        String message = "Created! ";
        redirectAttributes.addFlashAttribute("collectionsUpdated",message);
        return redirectView;
    }

    // update { collections }
    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public RedirectView updateCollection(RedirectAttributes redirectAttributes, @PathVariable("id") Long id, @ModelAttribute CollectionEvents collectionEvents) {
        collectionEventsService.update(id, collectionEvents);
        RedirectView redirectedView = new RedirectView("/", true);
        String message = "<b>Update sucess! </b>";
        redirectAttributes.addFlashAttribute("collections-updated", message);
        return redirectedView;
    }

    // delete {} 
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) throws NotFoundException {
         collectionEventsService.delete(id);
         String message = "<b>Deleted!</b>";
         redirectAttributes.addFlashAttribute("message", message);
         return "hello";
    }



}
