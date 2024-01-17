package com.ulas.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum EErrorType {
    INVALID_PARAMETER(1000,"Invalid parameter entered", BAD_REQUEST),
    METHOD_MIS_MATCH_ERROR(1001,"The value you entered does not match the desired value.", BAD_REQUEST),
    METHOD_NOT_VALID_ARGUMENT_ERROR(1002,"Missing parameter submission in URL",BAD_REQUEST),
    HTTP_MESSAGE_NOT_READABLE(1003,"HTTP message not readable" ,BAD_REQUEST ),
    USER_NOT_FOUND(2001,"User not found", BAD_REQUEST),
    USER_ALREADY_EXIST(2002,"User is already exist", BAD_REQUEST),
    INVALID_TOKEN(1004,"Invalid token", BAD_REQUEST),
    NOT_DECODED(1005,"Token can not decoded", INTERNAL_SERVER_ERROR),
    TOKEN_NOT_CREATED(1007,"Token can not be created", INTERNAL_SERVER_ERROR),
    UNEXPECTED_ERROR(1008,"Unexpected Error Occured", INTERNAL_SERVER_ERROR),
    USER_PASSWORD_UNMATCHED(2003,"User password unmatched ", BAD_REQUEST),
    LOGIN_ERROR_USERNAME_PASSWORD(2004,"Login Error ", BAD_REQUEST),
    FLIGHTS_NOT_FOUND(3001,"Flights not found",BAD_REQUEST),
    AIRPORT_NOT_FOUND(4001,"Airport not found",BAD_REQUEST);




    private int code;
    private String message;
    private HttpStatus httpStatus;
}
