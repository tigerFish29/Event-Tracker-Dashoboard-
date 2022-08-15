package com.eventtracker.events;

import static org.junit.jupiter.api.Assertions.assertEquals;




import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import com.eventtracker.events.Repository.UserRepository;
import com.eventtracker.events.Service.UserService;
import com.eventtracker.events.model.UserInfo;





@SpringBootTest
public class UserInfoTests {

    @Autowired
    private UserService service;

    @MockBean
    private UserRepository repo;
    

    @Test
    public void setUp() {
       UserInfo user = new UserInfo();

       Mockito.when(repo.save(user)).thenReturn(user);

       assertEquals(service.createUser(user), user);

       
    }

    @Test
    public void delete() {
        Mockito.doNothing().when(repo).delete(null);
    }

}