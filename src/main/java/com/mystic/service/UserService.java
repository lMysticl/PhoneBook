package com.mystic.service;

import com.mystic.model.entity.User;

import java.util.List;


public interface UserService {
    User findById(Long id);

    User findByUsername(String username);

    List<User> findAll();
}
