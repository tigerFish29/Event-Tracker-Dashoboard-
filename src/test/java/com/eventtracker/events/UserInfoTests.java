package com.eventtracker.events;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.eventtracker.events.Repository.UserRepository;
import com.eventtracker.events.Service.UserService;
import com.eventtracker.events.model.UserInfo;

@SpringBootTest(classes = {UserService.class, UserRepository.class})
@ActiveProfiles("test")
public class UserInfoTests {

    @Autowired
    UserService service;

    @MockBean
    UserRepository repo;

    @Test
    public void testCreate() {
        List<UserInfo> users = new ArrayList<>();
        users.add(new UserInfo());

        given(repo.findAll()).willReturn(users);

        List<UserInfo> expected  = service.getUsers();
        assertEquals(expected, users);
        verify(repo).findAll();
    }


    @Test
    public void shouldReturnAllusers() {
        List<UserInfo> user = new ArrayList();
        user.add(new UserInfo());

        given(repo.findAll()).willReturn(user);
        List<UserInfo> expected = service.getUsers();

        assertEquals(expected, user);
        verify(repo).findAll();
    }

    @Test 
    public void delete() {
        UserInfo user = new UserInfo();
        user.setFirstName("Brian");
        user.setLastName("Kings");
        user.setId(2);
        user.setDescription("Hi am a user");
        user.setRole("admin");
        user.setActive(true);

        when(repo.findById(user.getId())).thenReturn(Optional.of(user));

        service.removeUser(user.getId());
        verify(repo).deleteById(user.getId());
    }
    
}
