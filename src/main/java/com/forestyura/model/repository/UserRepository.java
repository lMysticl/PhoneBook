package com.forestyura.model.repository;

import com.forestyura.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

/**
 *  @author Putrenkov Pavlo
 */

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    ArrayList<User> findAll();
    Optional<User> findByUserId(Long id);
    Optional<User> findByUsername(String username);
    User saveAndFlush(User user);
}
