package com.cotivity.ticket_reservation_authorization_server.controller;


import com.cotivity.ticket_reservation_authorization_server.custom_exception.message_response.MessageResponse;
import com.cotivity.ticket_reservation_authorization_server.dto.UserDTO;
import com.cotivity.ticket_reservation_authorization_server.service.UserService;
import com.cotivity.ticket_reservation_authorization_server.utils.RequiredConstant;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(RequiredConstant.URLConstant.API_VERSION)
@AllArgsConstructor
public class UserController {

    private  final UserService userService;

    @PostMapping
    @RequestMapping(RequiredConstant.FeatureAPIConstant.USER_API)
    public ResponseEntity<MessageResponse> add(@Valid @RequestBody UserDTO userDTO) {
        userService.add(userDTO);
        return new ResponseEntity<>(new MessageResponse(RequiredConstant.MessageConstant.USER_CREATED), HttpStatus.OK);
    }
}
