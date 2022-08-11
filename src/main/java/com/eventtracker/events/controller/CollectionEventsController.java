package com.eventtracker.events.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    



}
