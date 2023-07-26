package com.swd.services;

import com.swd.entities.Role;
import com.swd.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role getById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    public Role getByName(String name) {
        return roleRepository.findByName(name);
    }



}
