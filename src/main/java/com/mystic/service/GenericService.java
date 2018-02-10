package com.mystic.service;

import com.mystic.model.entity.User;

import java.util.List;

/**
 * @author Putrenkov Pavlo
 */
public interface GenericService {
    User findByUsername(String username);

    List<User> findAllUsers();


}
