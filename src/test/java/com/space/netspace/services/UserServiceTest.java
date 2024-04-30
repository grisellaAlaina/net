package com.space.netspace.services;

import com.space.netspace.models.User;
import com.space.netspace.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    private static final int ID = 1;
    @Mock
    UserRepository userRepository;

    @InjectMocks
    private UserService service;


    @Test
    void findUser_shouldCallRepository() {
        final User user = mock(User.class);
        when(userRepository.findById(ID)).thenReturn(Optional.ofNullable(user));

        final User actual = service.findOne(ID);

        assertNotNull(actual);
        assertEquals(user, actual);
        verify(userRepository).findById(ID);
    }

    @Test
    void saveUser_shouldCallRepository() {
        final User user = mock(User.class);

        service.save(user);

        verify(userRepository).save(user);
    }




}