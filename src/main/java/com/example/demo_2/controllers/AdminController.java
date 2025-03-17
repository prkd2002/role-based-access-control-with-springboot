package com.example.demo_2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_2.dtos.RegisterUserDto;
import com.example.demo_2.model.User;
import com.example.demo_2.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RequestMapping("/admins")
@RestController
public class AdminController {

    @Autowired
    private  UserService userService;

    @PostMapping
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<User> createAdministrator(@RequestBody RegisterUserDto registerUserDto){
        User createdAdmin = userService.createAdministrator(registerUserDto);
        return ResponseEntity.ok(createdAdmin);
    }

}
