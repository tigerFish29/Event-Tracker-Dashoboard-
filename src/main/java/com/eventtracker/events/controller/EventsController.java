package com.eventtracker.events.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eventtracker.events.domain.Events;
import com.eventtracker.events.service.EventsService;

@Controller
public class EventsController {

    private final EventsService eventsService;

    // constructor 
    public EventsController(final EventsService eventsService) {
        this.eventsService = eventsService;
    }

    /**
     *   CRUD METHODS 
     */

     @RequestMapping(path = "/events", method = RequestMethod.GET)
     public String list(final Model model) {
        model.addAttribute("events", eventsService.findAll());
        return null; // dont have template yet 
     }


     @RequestMapping(path = "/events/{id}", method = RequestMethod.GET)
     public String getEvent(Model model, @PathVariable("id") Long id) {
        Events events = eventsService.get(id);
        model.addAttribute("events", events);
        return null; // we dont have a template 
     }

}