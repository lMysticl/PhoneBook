package com.mystic.service.impl;

import com.mystic.model.entity.User;
import com.mystic.model.repository.RoleRepository;
import com.mystic.model.repository.UserRepository;
import com.mystic.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author Putrenkov Pavlo
 */
@Service("userService")
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public User findByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }


    public User getUserService(@RequestBody String data) {
        JSONObject obj = new JSONObject(data);
        User user = new User();
        user.setFirstname(obj.getString("firstname"));
        user.setPassword(DigestUtils.sha256Hex(obj.getString("password").toLowerCase()));
        user.setMiddlename(obj.getString("middlename"));
        user.setLastname(obj.getString("firstname"));
        user.setUsername(obj.getString("username"));
        user.addRole(roleRepository.findOne(1L));
        return user;
    }


//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    public void save(User user) {
//        user.setPassword(passwordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findById(Long id) throws AccessDeniedException {
        return userRepository.findOne(id);
    }

    public List<User> findAll() throws AccessDeniedException {
        return userRepository.findAll();
    }
}
