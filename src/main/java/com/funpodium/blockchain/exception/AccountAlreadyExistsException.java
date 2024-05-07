package com.funpodium.blockchain.exception;


public class AccountAlreadyExistsException extends RuntimeException{

    public AccountAlreadyExistsException(String message){
        super(message);
    }

}
