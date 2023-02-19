package com.gfa.springadvanced.services;

import com.gfa.springadvanced.dtos.AuthDto;
import com.gfa.springadvanced.dtos.RegisterDto;
import com.gfa.springadvanced.models.UserEntity;

public interface UserEntityService {

    void createUser(UserEntity user);

    Boolean usernameExists(String username);

    AuthDto authenticate(RegisterDto registerDto);

    AuthDto register(RegisterDto registerDto);
}
