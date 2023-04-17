package com.api.management.service.user;

import com.api.management.dto.UserDTO;
import com.api.management.exception.UserNotFoundExeption;
import com.api.management.model.User;
import com.api.management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.api.management.mapper.UtilModelMapper.parseListObjects;
import static com.api.management.mapper.UtilModelMapper.parseObject;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDTO> findAll() {
        var entities = userRepository.findAll();

        return parseListObjects(entities, UserDTO.class);
    }

    @Override
    public UserDTO findById(Long userId) {
        var entity = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundExeption("Senha ou usuário em branco"));
        return parseObject(entity, UserDTO.class);
    }

    @Override
    public void create(UserDTO dto) {
        if(dto.getEmail() == null || dto.getPassword() == null || dto.getName() == null) {
            throw new RuntimeException();
        }
        userRepository.save(parseObject(dto, User.class));
    }
}
