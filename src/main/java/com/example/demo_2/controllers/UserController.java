package com.example.demo_2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_2.model.User;
import com.example.demo_2.service.JWTService;
import com.example.demo_2.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/users")
@RestController
public class UserController {

            @Autowired
    	    private UserService userService;

 
            @GetMapping("/me")
            @PreAuthorize("isAuthenticated()")
            public ResponseEntity<User> authenticatedUser(){
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

                User currentUSer = (User) authentication.getPrincipal();

                return ResponseEntity.ok(currentUSer);

            }
            
            @GetMapping("/")
            @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
            public ResponseEntity<List<User>> allUsers() {
                List <User> users = userService.allUsers();
                return ResponseEntity.ok(users);
            }



}
