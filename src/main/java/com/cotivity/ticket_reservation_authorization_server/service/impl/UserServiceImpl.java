package com.cotivity.ticket_reservation_authorization_server.service.impl;

import com.cotivity.ticket_reservation_authorization_server.dto.UserDTO;
import com.cotivity.ticket_reservation_authorization_server.service.UserService;
import com.cotivity.ticket_reservation_authorization_server.mapper.Mapper;
import com.cotivity.ticket_reservation_authorization_server.model.User;
import com.cotivity.ticket_reservation_authorization_server.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final Mapper<User, UserDTO> userMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public UserDTO add(UserDTO userDTO) {
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(encodedPassword);
        User user = userMapper.toEntity(userDTO);
        userRepository.save(user);
        userDTO.setId(user.getId());
        return userDTO;
    }
}
