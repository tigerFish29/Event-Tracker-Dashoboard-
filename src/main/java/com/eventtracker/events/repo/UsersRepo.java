package com.eventtracker.events.repo;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eventtracker.events.model.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {
      
    

    
}
