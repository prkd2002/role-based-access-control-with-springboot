package com.example.demo_2.bootsrap;

import java.util.Optional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo_2.dtos.RegisterUserDto;
import com.example.demo_2.model.Role;
import com.example.demo_2.model.RoleEnum;
import com.example.demo_2.model.User;
import com.example.demo_2.repository.RoleRepository;
import com.example.demo_2.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AdminSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.createSuperAdministrator();
    }

    private void createSuperAdministrator(){
        RegisterUserDto userDto = new RegisterUserDto();
        userDto.setFirstname("Super Admin");
        userDto.setLastname("Super Admin");
        userDto.setEmail("super.admin@email.com");
        userDto.setPassword(passwordEncoder.encode("123456"));

        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.SUPER_ADMIN);
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

        if(optionalRole.isEmpty() || optionalUser.isPresent()){
            return;
            
        }

        var user = new User();
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(optionalRole.get());

        userRepository.save(user);
    }

}
