package com.eventtracker.events.service;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventtracker.events.domain.Users;
import com.eventtracker.events.model.UsersDTO;
import com.eventtracker.events.repos.UserRepository;


@Service
public class UsersService {

    private final UserRepository userRepository;

    private ModelMapper mapper;

    private UsersDTO mapToDTO(Users users) {
        return mapper.map(users, UsersDTO.class);
    }

    // constructor 
    @Autowired
    public UsersService(UserRepository userRepository, ModelMapper mapper) {
        super();
        this.userRepository = userRepository;
        this.mapper = mapper;
    }


    public Users addUser(Users users) {
        return this.userRepository.save(users);
    }

    // find all users 
    public List<UsersDTO> findAll() {
        return userRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }
   
    
}
