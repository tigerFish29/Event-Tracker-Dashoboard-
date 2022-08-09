package com.eventtracker.events.service;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.eventtracker.events.domain.Users;
import com.eventtracker.events.model.UsersDTO;
import com.eventtracker.events.repos.UserRepository;

@Service
public class UsersService {

    private final UserRepository usersRepository;

    // constructor 
    public UsersService(final UserRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<UsersDTO> findAll() {
        return usersRepository.findAll().stream()
        .map(users -> mapToDTO(users, new UsersDTO())).collect(Collectors.toList());

    }

    // get 

    public UsersDTO get(final Long id) {
        return usersRepository.findById(id)
                .map(users -> mapToDTO(users, new UsersDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    // create 

    public Long create(final UsersDTO usersDTO) {
        final Users users = new Users();
        mapToEntity(usersDTO, users);
        return usersRepository.save(users).getId();

    }

    // update 
    public void update(final Long id, final UsersDTO usersDTO) {
        final Users users = usersRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(usersDTO, users);
        usersRepository.save(users);
    }

    // delete method 
    public void delete(final Long id) {
        usersRepository.deleteById(id);
    }
    

    private void mapToEntity(UsersDTO usersDTO, Users users) {
    }

    private UsersDTO mapToDTO(Users users, UsersDTO usersDTO) {
        return null;
    }
    
}
