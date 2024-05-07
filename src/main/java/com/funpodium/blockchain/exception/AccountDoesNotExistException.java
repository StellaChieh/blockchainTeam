package com.funpodium.blockchain.exception;

public class AccountDoesNotExistException extends RuntimeException {

    public AccountDoesNotExistException(String message){
        super(message);
    }

}
