package com.space.netspace.services;

import com.space.netspace.models.User;
import com.space.netspace.repositories.UserRepository;
import com.space.netspace.util.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    final static int ID = 1;

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Test
    void findByID_shouldCallRepository() {
        User user = mock(User.class);
        when(userRepository.findById(ID)).thenReturn(Optional.ofNullable(user));

        User currentUser = userService.findOne(ID);

        assertNotNull(currentUser);
        assertEquals(currentUser, user);
    }

    @Test
    void notFoundUser_shouldThrowException() {
        assertThrows(UserNotFoundException.class, () -> userService.findOne(ID));
    }

    @Test
    void saveUser_shouldCallRepository() {
        User user = mock(User.class);

        userService.save(user);

        verify(userRepository).save(user);
    }


}