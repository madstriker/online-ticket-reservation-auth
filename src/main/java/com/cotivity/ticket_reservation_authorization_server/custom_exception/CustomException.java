package com.cotivity.ticket_reservation_authorization_server.custom_exception;

import org.springframework.http.HttpStatus;

public class CustomException extends BaseException {

    public CustomException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public static class DataNotFound {
        public static CustomException dataNotFound(String message, HttpStatus status){
        return new CustomException(message, status);
    }
}
}
