package com.example.demo_2.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo_2.dtos.LoginUserDto;
import com.example.demo_2.dtos.RegisterUserDto;
import com.example.demo_2.model.Role;
import com.example.demo_2.model.RoleEnum;
import com.example.demo_2.model.User;
import com.example.demo_2.repository.RoleRepository;
import com.example.demo_2.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
public class AuthenticationService {

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Autowired
    private  AuthenticationManager authenticationManager;

    @Autowired	
    private RoleRepository roleRepository;
    

    public User signup(RegisterUserDto input){
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.USER);

        if(optionalRole.isEmpty()){
            return null;
        }
        User user = new User();
        user.setFirstname(input.getFirstname());
        user.setLastname(input.getLastname());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setEmail(input.getEmail());
        user.setRole(optionalRole.get());
        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input){
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                input.getEmail(), input.getPassword())
        );

        return userRepository.findByEmail(input.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User Was not found !"));
    }



}
