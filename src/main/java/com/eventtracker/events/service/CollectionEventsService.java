package com.eventtracker.events.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;


import com.eventtracker.events.domain.CollectionEvents;
import com.eventtracker.events.model.CollectionEventsDTO;
import com.eventtracker.events.repos.CollectionEventsRepository;
import com.eventtracker.events.repos.UserRepository;

@Service
public class CollectionEventsService {

    private CollectionEventsRepository collectionEventsRepository;
    private UserRepository usersRepository;

    private ModelMapper mapper;

    // mapping 
    private CollectionEventsDTO mapToDTO(CollectionEvents collections) {
        return mapper.map(collections, CollectionEventsDTO.class);
    }

    // constructor {}
    @Autowired
    public CollectionEventsService(CollectionEventsRepository collectionEventsRepository, UserRepository usersRepository, ModelMapper mapper) {
        super();
        this.collectionEventsRepository = collectionEventsRepository;
        this.usersRepository = usersRepository;
        this.mapper = mapper;
    }
    
    public CollectionEvents addEvent(CollectionEvents collectionEvents) {
        return this.collectionEventsRepository.save(collectionEvents);
    }

    // find all 
    public List<CollectionEventsDTO> findAll() {
        return collectionEventsRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    // get one {} 
    public CollectionEvents get(long id) {
        try {
            return collectionEventsRepository.findById(id).orElseThrow(NotFoundException::new);
        } catch (NotFoundException e) {
           
            e.printStackTrace();
        }
        return null;
    }

    // create 
    public CollectionEvents create(CollectionEvents collectionEvents) {
        return collectionEventsRepository.save(collectionEvents);
    }

    // update 
    

    // delete 
    public void delete(final Long id) {
        collectionEventsRepository.deleteById(id);
    }
}
