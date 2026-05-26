package com.ticket.stms.repository;

import java.util.Optional;

import com.ticket.stms.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository
        extends JpaRepository<User,Long>{

    Optional<User>
    findByEmail(
            String email
    );

}