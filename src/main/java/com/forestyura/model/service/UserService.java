package com.forestyura.model.service;

import com.forestyura.model.entity.User;
import com.forestyura.model.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Putrenkov Pavlo
 */

@Service("userService")
@Transactional
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public User findByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UsernameNotFoundException("Username '" + "' not found");
        }
    }

    public User saveUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    public boolean existsUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
