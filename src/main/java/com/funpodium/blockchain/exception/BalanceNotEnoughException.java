package com.funpodium.blockchain.exception;

public class BalanceNotEnoughException extends RuntimeException {

    public BalanceNotEnoughException(String message){
        super(message);
    }

}
