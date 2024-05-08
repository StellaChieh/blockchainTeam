package com.funpodium.blockchain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;


public class GlobalExceptionHandler {

    @ExceptionHandler(value=AccountAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleAccountAlreadyExistsException(AccountAlreadyExistsException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                            .body(new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage()));
    }

    @ExceptionHandler(value=AccountDoesNotExistException.class)
    public ResponseEntity<ErrorResponse> handleAccountDoesNotExistException(AccountDoesNotExistException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

    @ExceptionHandler(value=BalanceNotEnoughException.class)
    public ResponseEntity<ErrorResponse> handleBalanceNotEnoughException(BalanceNotEnoughException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleCommonException(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error"));
    }
}
