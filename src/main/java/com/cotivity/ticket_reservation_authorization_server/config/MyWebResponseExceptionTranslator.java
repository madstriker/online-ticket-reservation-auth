package com.cotivity.ticket_reservation_authorization_server.config;

import com.cotivity.ticket_reservation_authorization_server.custom_exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

public class MyWebResponseExceptionTranslator implements WebResponseExceptionTranslator {
    @Override
    public ResponseEntity<ErrorResponse> translate(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getCause().getLocalizedMessage(), null);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
}
