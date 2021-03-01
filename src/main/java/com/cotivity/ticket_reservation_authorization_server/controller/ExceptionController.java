package com.cotivity.ticket_reservation_authorization_server.controller;

import com.cotivity.ticket_reservation_authorization_server.custom_exception.CustomException;
import com.cotivity.ticket_reservation_authorization_server.custom_exception.ErrorResponse;
import com.cotivity.ticket_reservation_authorization_server.custom_exception.message_response.MessageResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> dataNotFound(HttpServletRequest  request, CustomException ex){
        ErrorResponse errorResponse = new ErrorResponse(ex.getLocalizedMessage(), request.getRequestURL().toString());
        return new ResponseEntity<>(errorResponse,  ex.getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult result = ex.getBindingResult();
        List<ObjectError> errors = result.getAllErrors();
        List<MessageResponse> messageResponses = new ArrayList<>();
        errors.stream().forEach(objectError -> {
            MessageResponse messageResponse = new MessageResponse( objectError.getDefaultMessage());
            messageResponses.add(messageResponse);
        });

        return handleExceptionInternal(ex, messageResponses ,new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
