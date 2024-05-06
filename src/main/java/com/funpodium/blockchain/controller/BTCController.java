package com.funpodium.blockchain.controller;

import java.time.LocalDateTime;

import com.funpodium.blockchain.service.IBtcTransactionService;
import com.funpodium.blockchain.model.BTCTransaction;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1")
public class BTCController {

    private final IBtcTransactionService btcTransactionService;

    @Autowired
    public BTCController(IBtcTransactionService btcTransactionService) {
        this.btcTransactionService = btcTransactionService;
    }

    @PostMapping("/btcs")
    public ResponseEntity<BTCTransaction> exchangeBTC(@RequestBody BTCTransaction btcTransaction) {
        BTCTransaction proceedTransaction = this.btcTransactionService.createBtcTransaction(btcTransaction, LocalDateTime.now());
        
        return new ResponseEntity<>(proceedTransaction, HttpStatus.CREATED);
    }
    
    
}
