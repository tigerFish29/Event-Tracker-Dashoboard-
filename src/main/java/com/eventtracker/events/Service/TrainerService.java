package com.eventtracker.events.Service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.eventtracker.events.Repository.TrainerRepo;
import com.eventtracker.events.model.Trainer;


@Service
public class TrainerService {

    private final TrainerRepo trainerRepo;

    public TrainerService(TrainerRepo trainerRepo) {
        this.trainerRepo = trainerRepo;
    }

    // get a trainer 
    public Trainer getTrainer(Integer id) {
        try {
            return trainerRepo.findByIdAndActive(id, true).orElseThrow(NotFoundException::new);
        } catch (NotFoundException e) {
            
            e.printStackTrace();
        }
        return null;
    }

    // create a trainer 
    public Trainer createTrainer(Trainer trainer) {
        return trainerRepo.save(trainer);
    }

    // get a list of trainers
    public List<Trainer> getTrainers() {
        return trainerRepo.findAll();
    }

    // update a trainer 
    public Trainer updateTrainer(Integer id, Trainer trainer) {
        Trainer fromSQL = getTrainer(id);
        fromSQL.setFirstName(trainer.getFirstName());
        fromSQL.setLastName(trainer.getLastName());
        fromSQL.setExperience(trainer.getExperience());
        fromSQL.setActive(trainer.isActive());
        fromSQL.setDescription(trainer.getDescription());
        fromSQL.setUpdatedAt(LocalDateTime.now());
        return trainerRepo.save(fromSQL);
    }
    
    // remove the trainer 
    public boolean removeTrainer(Integer id) {
        trainerRepo.deleteById(id);
        boolean exists = this.trainerRepo.existsById(id);
        if (exists == false) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                
        } else {
            return !exists;
        }
    }
    
    
    
}