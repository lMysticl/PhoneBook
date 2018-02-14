package com.mystic.service.impl;

import com.mystic.model.repository.RoleRepository;
import com.mystic.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Pavel Putrenkov
 * @version 1.0
 * @since
 */
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;


}
