package com.space.netspace.services;


import com.space.netspace.models.User;
import com.space.netspace.repositories.UserRepository;
import com.space.netspace.util.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void save(User user) {
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }
    public User findOne(int id) {
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.orElseThrow(UserNotFoundException::new);
    }

}
