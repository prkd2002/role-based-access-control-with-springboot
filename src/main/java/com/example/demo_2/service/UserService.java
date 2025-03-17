package com.example.demo_2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo_2.dtos.RegisterUserDto;
import com.example.demo_2.model.Role;
import com.example.demo_2.model.RoleEnum;
import com.example.demo_2.model.User;
import com.example.demo_2.repository.RoleRepository;
import com.example.demo_2.repository.UserRepository;

@Service
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder;

   
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    UserService(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

  

    public List<User> allUsers(){
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public User createAdministrator(RegisterUserDto input){
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.ADMIN);
        if(optionalRole.isEmpty()){
            return null;
        }

        var user = new User();
        user.setFirstname(input.getFirstname());
        user.setLastname(input.getLastname());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setRole(optionalRole.get());
        user.setEmail(input.getEmail());

        return userRepository.save(user);
        
    }

}
