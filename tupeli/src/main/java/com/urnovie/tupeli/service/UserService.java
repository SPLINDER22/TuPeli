package com.urnovie.tupeli.service;

import com.urnovie.tupeli.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    List<UserDTO> getAllActiveUsers();

    UserDTO getUserById(long id);

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(long id, UserDTO userDTO);

    boolean deleteUser(long id);
}
