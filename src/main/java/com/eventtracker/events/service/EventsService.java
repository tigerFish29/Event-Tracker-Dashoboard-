package com.eventtracker.events.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.eventtracker.events.domain.Events;
import com.eventtracker.events.model.EventsDTO;
import com.eventtracker.events.repos.EventsRepository;

@Service
public class EventsService {

    private EventsRepository eventsRepository;

    private ModelMapper mapper; 

    // mapping 
    private EventsDTO mapToDTO(Events events) {
        return mapper.map(events, EventsDTO.class);
    }

    // constructor 
    @Autowired
    public EventsService(EventsRepository eventsRepository, ModelMapper mapper) {
        super();
        this.eventsRepository = eventsRepository;
        this.mapper = mapper;
    }

    public Events addEvent(Events events) {
        return this.eventsRepository.save(events);
    }
    // find all()
    public List<EventsDTO> findAll() {
        return eventsRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    // get one {} 
    public Events get(long id) {
        try {
            return eventsRepository.findById(id).orElseThrow(NotFoundException::new);
        } catch (NotFoundException e) {
           
            e.printStackTrace();
        }
        return null;
    }
    
    // create{} 
    public Events create(Events events) {
        return eventsRepository.save(events);
    }

    // update 
    public Events update(final Long id, final Events events) {
        Events fromDB = get(id);
        fromDB.setDate(events.getDate());
        fromDB.setTitle(events.getTitle());
        fromDB.setDescription(events.getDescription());
        return eventsRepository.save(fromDB);
    }

}