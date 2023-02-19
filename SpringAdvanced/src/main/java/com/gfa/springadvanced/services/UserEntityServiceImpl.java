package com.gfa.springadvanced.services;

import com.gfa.springadvanced.dtos.AuthDto;
import com.gfa.springadvanced.dtos.RegisterDto;
import com.gfa.springadvanced.models.Role;
import com.gfa.springadvanced.models.UserEntity;
import com.gfa.springadvanced.repositories.UserRepository;
import com.gfa.springadvanced.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserEntityServiceImpl implements UserEntityService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserEntityServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                                 RoleService roleService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void createUser(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public Boolean usernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public AuthDto register(RegisterDto registerDto) {

        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Role roles = roleService.roleByName("USER").get();
        user.setRoles(Collections.singletonList(roles));

        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);
        return AuthDto.builder().token(jwtToken).build();
    }

    @Override
    public AuthDto authenticate(RegisterDto registerDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        registerDto.getUsername(),
                        registerDto.getPassword()));

        UserEntity user = userRepository.findByUsername(registerDto.getUsername()).get();
        String jwtToken = jwtService.generateToken(user);
        return AuthDto.builder().token(jwtToken).build();
    }
}
