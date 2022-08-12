package com.eventtracker.events.service;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import com.eventtracker.events.domain.CollectionEvents;

import com.eventtracker.events.repos.CollectionEventsRepository;


@Service
public class CollectionEventsService {

    private CollectionEventsRepository collectionEventsRepository;
  
    // constructor {}
    @Autowired
    public CollectionEventsService(CollectionEventsRepository collectionEventsRepository, ModelMapper mapper) {
        super();
        this.collectionEventsRepository = collectionEventsRepository;
        
    }
    
    public CollectionEvents addEvent(CollectionEvents collectionEvents) {
        return this.collectionEventsRepository.save(collectionEvents);
    }
    
    // find all 
   public List<CollectionEvents> findAll() {
       return collectionEventsRepository.findAll();
        
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
    public CollectionEvents update(final Long id, final CollectionEvents collectionEvents) {
        Optional<CollectionEvents> collections = this.collectionEventsRepository.findById(id);
        CollectionEvents current = collections.orElse(new CollectionEvents());
        current.setName(collectionEvents.getName());
        current.setAddress(collectionEvents.getAddress());
        current.setCity(collectionEvents.getCity());
        current.setRegion(collectionEvents.getRegion());
        current.setPostcode(collectionEvents.getPostcode());
        current.setCountry(collectionEvents.getCountry());
        current.setContinent(collectionEvents.getContinent());
        
        return this.collectionEventsRepository.save(current);
    
    }

    // delete 
    public void delete(final Long id) throws NotFoundException {
        Optional<CollectionEvents> collections = this.collectionEventsRepository.findById(id);
        if (collections.isPresent()) {
            this.collectionEventsRepository.deleteById(id);
        } else {
            throw new NotFoundException();
        }
    }
        
    
}
