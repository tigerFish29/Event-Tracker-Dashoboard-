package com.eventtracker.events.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventtracker.events.domain.Events;

public interface EventsRepository extends JpaRepository <Events, Long> {
    
}
