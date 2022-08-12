package com.eventtracker.events.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.eventtracker.events.domain.Users;
import com.eventtracker.events.repos.UserRepository;

@Service
public class UsersService {

    private  UserRepository usersRepository;

    // constructor 
    @Autowired
    private Users addUser(Users users) {
        return this.usersRepository.save(users);
    }

    // find all 
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    // get one {} 
    public Users get(Long id) {
        try {
            return usersRepository.findById(id).orElseThrow(NotFoundException::new);
        } catch (NotFoundException e) {
           
            e.printStackTrace();
        }
        return null;
    }

    // create users 
    public Users create(Users users) {
        return usersRepository.save(users);
    }

    // update the user {} 
    public Users update(final Long id, final Users users) {
        Users fromDB = get(id);
        fromDB.setFirstName(users.getFirstName());
        fromDB.setLastName(users.getLastName());
        fromDB.setEmail(users.getEmail());
        fromDB.setAddress(users.getAddress());
        return usersRepository.save(fromDB);
    }


    // delete the user {} 
    public void delete(final Long id) throws NotFoundException {
        Optional<Users> users = this.usersRepository.findById(id);
        if (users.isPresent()) {
            this.usersRepository.deleteById(id);
        } else {
            throw new NotFoundException();
        }
        
    }
    

   
    
}
