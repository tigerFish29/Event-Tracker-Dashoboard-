package com.eventtracker.events.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.eventtracker.events.model.Users;
import com.eventtracker.events.repo.UsersRepo;

@Service
public class UsersService {
    
    private final UsersRepo repo; 
    
    public UsersService(final UsersRepo repo) {
        this.repo = repo;
    }


    // return all 
    public List<Users> getAllUsers() {
        return repo.findAll();
    }
    

    // find a single user 
    public Users get(final Long id) {
        return repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    // create 
    public Long create(final Users user) {
        return repo.save(user).getId();
    }

    // update the user 
    public void update(final Long id, final Users user) {
        final Users current = repo.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        repo.save(current);
    }

    // delete 
    public void delete(final Long id) {
        repo.deleteById(id);
    }
}
