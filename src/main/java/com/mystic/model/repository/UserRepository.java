package com.mystic.model.repository;

import com.mystic.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @author Putrenkov Pavlo
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    ArrayList<User> findAll();

    Optional<User> findByUserId(Long id);

    Optional<User> findByUsername(String username);

    User saveAndFlush(User user);


}
