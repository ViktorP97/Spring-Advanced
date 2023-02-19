package com.gfa.springadvanced.controllers;

import com.gfa.springadvanced.dtos.AuthDto;
import com.gfa.springadvanced.dtos.RegisterDto;
import com.gfa.springadvanced.services.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class MainRestController {

    private final UserEntityService userService;

    @Autowired
    public MainRestController(UserEntityService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDto registerDto) {

        if (userService.usernameExists(registerDto.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(userService.register(registerDto));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthDto> authenticate(@RequestBody RegisterDto registerDto) {
        return ResponseEntity.ok(userService.authenticate(registerDto));
    }
}
