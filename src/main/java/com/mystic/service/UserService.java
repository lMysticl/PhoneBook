package com.mystic.service;

import com.mystic.model.entity.User;

import java.util.List;

/**
 * @author Putrenkov Pavlo
 */
public interface UserService {
    User findById(Long id);

    User findByUsername(String username);

    List<User> findAll();

    User getUserService(String data);
}
