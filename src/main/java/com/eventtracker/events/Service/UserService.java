package com.eventtracker.events.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.eventtracker.events.Repository.UserRepository;
import com.eventtracker.events.model.UserInfo;



@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // get a user 
    public UserInfo getUser(Integer id) {
        try {
            return userRepository.findByIdAndActive(id, true).orElseThrow(NotFoundException::new);
        } catch (NotFoundException e) {
            
            e.printStackTrace();
        }
        return null;
    }

    // create a user {} 
    public UserInfo createUser(UserInfo userInfo) {
        return userRepository.save(userInfo);
    }

   
    

    // get users {} 
    public List<UserInfo> getUsers() {
        return userRepository.findAllByActiveOrderByIdDesc(true);
    }

    // update a user{}
    public UserInfo updateUser(Integer id, UserInfo request) {
        UserInfo fromDB = getUser(id);
        fromDB.setFirstName(request.getFirstName());
        fromDB.setLastName(request.getLastName());
        fromDB.setDescription(request.getDescription());
        fromDB.setRole(request.getRole());
        fromDB.setActive(request.isActive());
        fromDB.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(fromDB);
    }

    // remove the user 
    public boolean removeUser(Integer id) {
        userRepository.deleteById(id);
        boolean current = this.userRepository.existsById(id);
        if (current == false) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return !current;
        }
    }
    
}
