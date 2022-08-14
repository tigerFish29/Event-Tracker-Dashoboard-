package com.eventtracker.events.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eventtracker.events.model.Trainer;

@Repository
public interface TrainerRepo extends JpaRepository<Trainer, Integer> {
    Optional<Trainer> findByIdAndActive(Integer id, boolean active);
}
