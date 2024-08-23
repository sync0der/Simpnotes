package com.syncoder.simpnotes.services;

import com.syncoder.simpnotes.domain.Role;
import com.syncoder.simpnotes.domain.User;
import com.syncoder.simpnotes.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null)
            return false;

        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.USER);
        userRepository.save(user);
        return true;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

}
