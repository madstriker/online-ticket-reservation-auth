package com.cotivity.ticket_reservation_authorization_server.dto;

import com.cotivity.ticket_reservation_authorization_server.utils.RequiredConstant;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private Long id;
    @NotBlank(message = RequiredConstant.FieldValidatorConstant.USERNAME_REQUIRED)
    private String username;
    @NotBlank(message = RequiredConstant.FieldValidatorConstant.PASSWORD_REQUIRED)
    private String password;
}
