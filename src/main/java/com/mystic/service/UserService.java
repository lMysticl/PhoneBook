package com.mystic.service;

import com.mystic.model.entity.User;
import com.mystic.model.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Putrenkov Pavlo
 */

@Service("userService")
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    public User saveUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    public boolean existsUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }


}
