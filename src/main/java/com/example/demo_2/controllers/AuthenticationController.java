package com.example.demo_2.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_2.dtos.LoginUserDto;
import com.example.demo_2.dtos.RegisterUserDto;
import com.example.demo_2.model.User;
import com.example.demo_2.service.AuthenticationService;
import com.example.demo_2.service.JWTService;
import com.example.demo_2.response.LoginResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import lombok.RequiredArgsConstructor;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);


    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto){
        logger.info(registerUserDto.getEmail());
        logger.info(registerUserDto.getFirstname());
        logger.info(registerUserDto.getLastname());
        logger.info(registerUserDto.getPassword());
        User registeredUSer = authenticationService.signup(registerUserDto);
        return ResponseEntity.ok(registeredUSer);

    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate (@RequestBody LoginUserDto loginUserDto) {
            
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
    

}
