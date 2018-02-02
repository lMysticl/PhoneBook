package com.mystic.model.repository;

import com.mystic.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @author Pavel Putrenkov
 * @version 1.0
 * @since
 */
public interface UserRepository extends JpaRepository<User, Long> {

    ArrayList<User> findAll();

    Optional<User> findByUserId(Long id);

    User findByUsername(String username);

    User saveAndFlush(User user);

}
