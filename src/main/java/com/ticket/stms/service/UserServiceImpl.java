package com.ticket.stms.service;

import com.ticket.stms.dto.UserRegisterDTO;
import com.ticket.stms.dto.LoginDTO;
import com.ticket.stms.entity.User;
import com.ticket.stms.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserServiceImpl
        implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired

    BCryptPasswordEncoder
            passwordEncoder;

    @Override

    public User registerUser(
            UserRegisterDTO dto
    ){

        if(
                userRepository
                        .findByEmail(
                                dto.getEmail()
                        ).isPresent()
        ){

            throw new RuntimeException(
                    "Email exists"
            );

        }

        User user=
                new User();

        user.setName(
                dto.getName()
        );

        user.setEmail(
                dto.getEmail()
        );

        user.setPassword(

                passwordEncoder.encode(
                        dto.getPassword()
                )

        );

        user.setRole(
                dto.getRole()
        );

        user.setSpecialization(
                dto.getSpecialization()
        );

        return userRepository.save(
                user
        );

    }

    @Override

    public List<User>
    getAllUsers(){

        return userRepository.findAll();

    }
    @Override

    public String loginUser(
            LoginDTO dto
    ){

        User user=
                userRepository
                        .findByEmail(
                                dto.getEmail()
                        )

                        .orElse(null);

        if(user==null){

            return
                    "User not found";

        }

        boolean match=

                passwordEncoder.matches(

                        dto.getPassword(),

                        user.getPassword()

                );

        if(match){

            return
                    "Login Success";

        }

        return
                "Wrong Password";

    }

}