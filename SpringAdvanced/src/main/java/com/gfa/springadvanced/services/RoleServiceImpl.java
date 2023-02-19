package com.gfa.springadvanced.services;

import com.gfa.springadvanced.models.Role;
import com.gfa.springadvanced.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<Role> roleByName(String role) {
        return roleRepository.findByName(role);
    }
}
