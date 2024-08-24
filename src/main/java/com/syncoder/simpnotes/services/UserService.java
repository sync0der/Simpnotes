package com.syncoder.simpnotes.services;

import com.syncoder.simpnotes.domain.Role;
import com.syncoder.simpnotes.domain.User;
import com.syncoder.simpnotes.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Not Found!"));
    }

    public void changeUserRole(User user, Map<String, String> form) {
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key))
                user.getRoles().add(Role.valueOf(key));
        }
        userRepository.save(user);
    }


}
