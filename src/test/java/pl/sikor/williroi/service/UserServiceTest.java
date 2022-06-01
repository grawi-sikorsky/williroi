package pl.sikor.williroi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pl.sikor.williroi.model.UserModel;
import pl.sikor.williroi.respository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;
    
    @Test
    void addNewUser_should_AddUser(){
        //given
        UserModel testuser = new UserModel();
        testuser.setUsername("testuser");

        //when
        when(userRepository.findByUsername(testuser.getUsername())).thenReturn(null);

        UserModel responseUser = userService.addNewUser(testuser);
        verify(userRepository, times(1)).save(responseUser);
    }

    @Test
    void getUser_should_returnUser(){
        UserModel testuser = new UserModel();
        testuser.setUsername("testuser");

        when(userRepository.findByUsername(testuser.getUsername())).thenReturn(testuser);

        assertEquals(userService.getUser(testuser.getUsername()), testuser);
    }

    @Test
    void addHeliumAccount_should_addAccount(){
        
    }

}