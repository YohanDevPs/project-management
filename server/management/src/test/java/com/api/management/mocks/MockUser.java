package com.api.management.mocks;

import com.api.management.dto.UserDTO;
import com.api.management.models.User;

import java.util.ArrayList;
import java.util.List;

public class MockUser {

    public User mockEntity() {
        return mockEntity(0);
    }

    public UserDTO mockDTO() {
        return mockDTO(0);
    }

    public List<User> mockEntityList() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            users.add(mockEntity(i));
        }
        return users;
    }

    public List<UserDTO> mockDTOList() {
        List<UserDTO> usersDTOS = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            usersDTOS.add(mockDTO(i));
        }
        return usersDTOS;
    }

    public User mockEntity(Integer number) {
        User user = new User();

        user.setId(number.longValue());
        user.setName("Yohan" + number);
        user.setPassword("123456" + number);

        return user;
    }

    public UserDTO mockDTO(Integer number) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(number.longValue());
        userDTO.setName("Yohan" + number);
        userDTO.setPassword("123456" + number);

        return userDTO;
    }
}
