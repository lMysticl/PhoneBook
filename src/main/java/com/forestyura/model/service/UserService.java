package com.forestyura.model.service;

import com.forestyura.model.entity.User;
import com.forestyura.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by Yuriy Vlasiuk on 07.03.2017.
 */

@Service("userService")
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

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

    public  boolean existsUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
