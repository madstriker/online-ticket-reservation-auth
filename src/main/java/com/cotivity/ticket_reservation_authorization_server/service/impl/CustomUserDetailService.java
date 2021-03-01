package com.cotivity.ticket_reservation_authorization_server.service.impl;

import com.cotivity.ticket_reservation_authorization_server.model.CustomUserDetail;
import com.cotivity.ticket_reservation_authorization_server.model.User;
import com.cotivity.ticket_reservation_authorization_server.custom_exception.CustomException;
import com.cotivity.ticket_reservation_authorization_server.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> CustomException.DataNotFound.dataNotFound("User not found !!!", HttpStatus.NOT_FOUND));
        return new CustomUserDetail(user);
    }
}
