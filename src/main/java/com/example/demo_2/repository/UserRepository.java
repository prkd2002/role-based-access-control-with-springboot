package com.example.demo_2.repository;

import org.springframework.stereotype.Repository;

import com.example.demo_2.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface  UserRepository extends JpaRepository<User,Integer>  {
    Optional<User> findByEmail(String email);

}
