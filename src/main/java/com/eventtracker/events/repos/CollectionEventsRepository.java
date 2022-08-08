package com.eventtracker.events.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventtracker.events.domain.CollectionEvents;

public interface CollectionEventsRepository extends JpaRepository <CollectionEvents, Long> {
    
}
    

