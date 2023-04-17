package com.api.management.service.user;

import com.api.management.dto.UserDTO;
import com.api.management.model.User;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll();
    UserDTO findById(Long userId);
    void create(UserDTO dto);
}
