package com.mystic.model.repository;

import com.mystic.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Pavel Putrenkov
 * @version 1.0
 * @since
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

}
