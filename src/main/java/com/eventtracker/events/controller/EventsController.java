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

import com.eventtracker.events.domain.Events;
import com.eventtracker.events.service.EventsService;

@RequestMapping
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

     // get a single event 
     @RequestMapping(path = "/events/{id}", method = RequestMethod.GET)
     public String getEvent(Model model, @PathVariable("id") Long id) {
        Events events = eventsService.get(id);
        model.addAttribute("events", events);
        return null; // we dont have a template 
     }

     // create an event 
     @RequestMapping(path = "/create", method = RequestMethod.POST) 
        public RedirectView createEvent(RedirectAttributes redirects, @ModelAttribute Events events) {
            eventsService.create(events);
            String message = "Event has been created !";
            redirects.addFlashAttribute("events", message);
            return null; // need template 
        }


     // update the events 
     @RequestMapping(path = "/update", method = RequestMethod.POST)
      public RedirectView updateUser(RedirectAttributes redirects, @PathVariable("id") Long id, @ModelAttribute Events events) {
        eventsService.update(id, events);
        RedirectView redirectView = new RedirectView("/", true);
        String message = "<b>Event has now been updated!</b>";
        redirects.addFlashAttribute("eventUpdate", message);
        return redirectView;
      }

      @PostMapping("/delete/{id}")
      public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) throws NotFoundException{
        eventsService.delete(id);
        String message = "<b>Deleted!</b>";
        redirectAttributes.addFlashAttribute("message", message);
        return "hello";
      }    
    
}
