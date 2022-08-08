package com.eventtracker.events.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventtracker.events.domain.Users;



public interface UserRepository extends JpaRepository <Users, Long > {
   
}