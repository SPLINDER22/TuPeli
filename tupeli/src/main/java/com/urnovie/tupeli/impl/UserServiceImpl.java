package com.urnovie.tupeli.impl;

import com.urnovie.tupeli.dto.UserDTO;
import com.urnovie.tupeli.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<UserDTO> getAllUsers() {
        return List.of();
    }

    @Override
    public List<UserDTO> getAllActiveUsers() {
        return List.of();
    }

    @Override
    public UserDTO getUserById(long id) {
        return null;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public UserDTO updateUser(long id, UserDTO userDTO) {
        return null;
    }

    @Override
    public boolean deleteUser(long id) {
        return false;
    }
}
