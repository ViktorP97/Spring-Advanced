package com.gfa.springadvanced.services;

import com.gfa.springadvanced.models.Role;

import java.util.Optional;

public interface RoleService {

    Optional<Role> roleByName(String role);
}
