package com.ticket.stms.service;

import com.ticket.stms.dto.UserRegisterDTO;
import com.ticket.stms.dto.LoginDTO;
import com.ticket.stms.entity.User;

import java.util.List;

public interface UserService {

    User registerUser(
            UserRegisterDTO dto
    );

    List<User> getAllUsers();
    String loginUser(
            LoginDTO dto
    );
}
