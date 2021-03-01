package com.cotivity.ticket_reservation_authorization_server.mapper.user;

import com.cotivity.ticket_reservation_authorization_server.dto.UserDTO;
import com.cotivity.ticket_reservation_authorization_server.mapper.Mapper;
import com.cotivity.ticket_reservation_authorization_server.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper implements Mapper<User, UserDTO> {

    public User toEntity(UserDTO userDTO){
        User user = new User();
        user.setPassword(userDTO.getPassword());
        user.setUsername(userDTO.getUsername());
        return user;
    }
}
