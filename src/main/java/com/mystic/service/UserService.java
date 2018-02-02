package com.mystic.service;

import com.mystic.model.entity.User;
import com.mystic.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Putrenkov Pavlo
 */

@Service("userService")
//@AllArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    public User findByUsername(String username) throws UsernameNotFoundException {
//        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
//    }
    public User findByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
//
//    public User saveUser(User user) {
//        return userRepository.saveAndFlush(user);
//    }
//
//    public boolean existsUsername(String username) {
//        return userRepository.findByUsername(username).isPresent();
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public void save(User user){
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    public User getUser(String username){
        return userRepository.findByUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


}
